package com.example.moviestack.ui.dashboard.mylists.mylistdetail

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviestack.pojo.MyList
import com.example.moviestack.roomdb.bookmark.BookmarkHelperI
import com.example.moviestack.roomdb.mylist.MyListHelperI
import com.example.moviestack.roomdb.mylistdetail.MyListDetailHelper
import com.example.moviestack.roomdb.mylistdetail.MyListDetailHelperI
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.common.movielist.MovieListState
import com.example.moviestack.ui.common.movielist.adapter.MovieListAdapter
import com.example.moviestack.ui.common.movielist.simplelist.MovieListFragment
import com.example.moviestack.ui.dashboard.mylists.mylistdetail.adapter.MyListDetailAdapter
import com.example.moviestack.ui.moviedetail.DetailData
import com.example.moviestack.ui.moviedetail.info.dialog.adapter.MyListAdapter

class MyListDetailViewModel(var myListHelperI: MyListHelperI) : ViewModel() {

    var mutableLiveData: MutableLiveData<MyListDetailState> = MutableLiveData()


    var state = MyListDetailState()
        set(value) {
            field = value
            publishState(value)
        }

    fun getResult(activity: Activity,myListType : MyList.Type){

        var type= if(myListType==MyList.Type.MOVIE)
            ListType.Type.MY_LIST_MOVIE
        else
            ListType.Type.MY_LIST_TV_SHOW

      state = state.copy(
          myListDetailAdapter = MyListDetailAdapter(activity,myListHelperI.getAllMyListMovie(myListType),type)
      )


    }

    private fun publishState(state: MyListDetailState) {
        mutableLiveData.postValue(state)
    }

}