package com.example.moviestack.ui.common.movielist.simplelist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviestack.R
import com.example.moviestack.api.DataHelper
import com.example.moviestack.databinding.SimilarFragmentBinding
import com.example.moviestack.pojo.MyListDetail
import com.example.moviestack.roomdb.LocaleDataBase
import com.example.moviestack.roomdb.RoomDatabaseHelper
import com.example.moviestack.roomdb.bookmark.BookmarkHelper
import com.example.moviestack.roomdb.movieInfo.MovieListViewModel
import com.example.moviestack.roomdb.mylistdetail.MyListDetailHelper
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.moviedetail.DetailData
import com.example.moviestack.utils.createFactory

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
        return mView
    }


    private fun loadData() {
        when (listType.type) {
            ListType.Type.MOVIE_CREDITS -> movieListViewModel.getMovieCredits(listType.data,DataHelper().personRepositoryI,detailDataType=DetailData.Type.MOVIE)
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

        })
    }

}
