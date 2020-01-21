package com.example.moviestack.ui.dashboard.mylists.mylistdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviestack.R
import com.example.moviestack.databinding.MyListDataBinding
import com.example.moviestack.pojo.MyList

class MyListAdapter(private val list: List<MyList>): RecyclerView.Adapter<MyListAdapter.MyListDetailViewHolder>() {

    inner class MyListDetailViewHolder(val myListDataBinding : MyListDataBinding) : RecyclerView.ViewHolder(myListDataBinding.root) {
        fun bind(result: MyList) {
            this.myListDataBinding.mylist = result
            this.myListDataBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListAdapter.MyListDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val similarDataBinding : MyListDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.my_list_detail,parent,false)
        return MyListDetailViewHolder(similarDataBinding)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: MyListDetailViewHolder, position: Int) {
        holder.bind(list[position])
    }

}