package com.example.moviestack.ui.moviedetail.info.dialog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.databinding.MyListsDataBinding
import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.pojo.MyList
import com.example.moviestack.pojo.MyListDetail
import com.example.moviestack.roomdb.RoomDatabaseHelper
import com.example.moviestack.roomdb.mylistdetail.MyListDetailHelper
import com.example.qrcode.roomdb.utils.MovieInfoHelper

class MyListAdapter(
    private val movieInfo: MovieInfo,
    val list: List<MyList>,
    val type: MyListDetail.Type
)  : RecyclerView.Adapter<MyListAdapter.MyListViewHolder>() {

    inner class MyListViewHolder(val Binding: MyListsDataBinding) : RecyclerView.ViewHolder(Binding.root) {

        fun bind(result: MyList?) {
            this.Binding.myList = result
            this.Binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val mylist: MyListsDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.my_list_item,parent,false)
        return MyListViewHolder(mylist)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        val myListDetailHelper = MyListDetailHelper(RoomDatabaseHelper().localeDataBase)
        val movieInfoHelper = MovieInfoHelper(RoomDatabaseHelper().localeDataBase)
                holder.Binding.checkBox.setOnCheckedChangeListener(null)
        holder.Binding.checkBox.setChecked(myListDetailHelper.hasMyListDetail(list.get(position).id,movieInfo.id!!,type))
        holder.Binding.checkBox.setOnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked){
                if(!movieInfoHelper.hasMovie("${movieInfo.id}"))
                    movieInfoHelper.insertResult(movieInfo)
                  myListDetailHelper.insertMyListDetail(MyListDetail(movieInfoId = movieInfo.id!!,myListId = list.get(position).id,type = type))
            }
            else{
                myListDetailHelper.deleteMyListDetail(movieInfoId = movieInfo.id!!,myListId = list.get(position).id,type = type)
            }
        }
        holder.bind(list[position])
    }

}