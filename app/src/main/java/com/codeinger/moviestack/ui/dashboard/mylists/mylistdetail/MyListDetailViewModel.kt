package com.codeinger.moviestack.ui.dashboard.mylists.mylistdetail

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codeinger.moviestack.pojo.MyList
import com.codeinger.moviestack.roomdb.mylist.MyListHelperI
import com.codeinger.moviestack.ui.common.ListType
import com.codeinger.moviestack.ui.dashboard.mylists.mylistdetail.adapter.MyListDetailAdapter

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