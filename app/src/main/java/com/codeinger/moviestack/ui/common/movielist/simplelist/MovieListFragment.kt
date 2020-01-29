package com.codeinger.moviestack.ui.common.movielist.simplelist


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codeinger.moviestack.R
import com.codeinger.moviestack.api.DataHelper
import com.codeinger.moviestack.databinding.SimilarFragmentBinding
import com.codeinger.moviestack.pojo.MyListDetail
import com.codeinger.moviestack.roomdb.RoomDatabaseHelper
import com.codeinger.moviestack.roomdb.bookmark.BookmarkHelper
import com.codeinger.moviestack.roomdb.movieInfo.MovieListViewModel
import com.codeinger.moviestack.roomdb.mylistdetail.MyListDetailHelper
import com.codeinger.moviestack.ui.common.ListType
import com.codeinger.moviestack.ui.moviedetail.DetailData
import com.codeinger.moviestack.utils.createFactory
import kotlinx.android.synthetic.main.fragment_my_list_detail.view.*

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment() {

    private lateinit var listType: ListType
    private lateinit var mView: View
    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var binding: SimilarFragmentBinding

    companion object {
        private const val ID = "Id"
        fun newInstance(listType: ListType): MovieListFragment {
            val bundle = Bundle()
            bundle.putParcelable(ID, listType)
            val fragment =
                MovieListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_similar, container, false)
        init()
        loadData()
        setObserver()
        setRefreshLayout()

        return mView
    }



    private fun loadData() {
        Log.i("dsjcbsdjbc","0 $listType.type")
        when (listType.type) {
            ListType.Type.MOVIE_CREDITS -> {
                Log.i("dsjcbsdjbc","0 ")
                movieListViewModel.getMovieCredits(listType.data,DataHelper().personRepositoryI,detailDataType=DetailData.Type.MOVIE)
            }
            ListType.Type.TV_CREDITS -> movieListViewModel.getTvCredits(listType.data,DataHelper().personRepositoryI,detailDataType=DetailData.Type.TV_SHOW)
            ListType.Type.BOOKMARK_MOVIE->movieListViewModel.getResult( bookmarkHelperI  = BookmarkHelper(RoomDatabaseHelper().localeDataBase),type = DetailData.Type.MOVIE)
            ListType.Type.BOOKMARK_TV_SHOW->movieListViewModel.getResult( bookmarkHelperI = BookmarkHelper(RoomDatabaseHelper().localeDataBase),type = DetailData.Type.TV_SHOW)
            ListType.Type.MY_LIST_MOVIE->movieListViewModel.getMyList( myListDetailHelper  = MyListDetailHelper(RoomDatabaseHelper().localeDataBase),type = MyListDetail.Type.MOVIE,myListId = listType.data.toInt(),detailDataType=DetailData.Type.MOVIE)
            ListType.Type.MY_LIST_TV_SHOW->movieListViewModel.getMyList( myListDetailHelper  = MyListDetailHelper(RoomDatabaseHelper().localeDataBase),type = MyListDetail.Type.TV_SHOW,myListId = listType.data.toInt(),detailDataType=DetailData.Type.TV_SHOW)
        }
    }


    private fun init() {
        listType = arguments?.getParcelable<ListType>(
            ID
        ) as ListType
        mView = binding.root
        val factory = MovieListViewModel()
            .createFactory()
        movieListViewModel = ViewModelProvider(this, factory).get(MovieListViewModel::class.java)
    }

    private fun setObserver() {
        movieListViewModel.mutableLiveData.observe(this, Observer {
            binding.movieListState = it
            mView.swipeContainer.isRefreshing = false
        })
    }

    private fun setRefreshLayout() {
        when (listType.type) {
            ListType.Type.MOVIE_CREDITS,ListType.Type.TV_CREDITS -> mView.swipeContainer.isEnabled = false

            ListType.Type.BOOKMARK_MOVIE,
            ListType.Type.BOOKMARK_TV_SHOW,
            ListType.Type.MY_LIST_MOVIE,
            ListType.Type.MY_LIST_TV_SHOW -> mView.swipeContainer.isEnabled = true
        }

        mView.swipeContainer.setOnRefreshListener{
            mView.swipeContainer.isRefreshing = true
            loadData()
        }
    }


}
