package com.example.moviestack.ui.dashboard.mylists.mylistdetail.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.example.moviestack.R
import com.example.moviestack.databinding.MyListDataBinding
import com.example.moviestack.pojo.MyList
import com.example.moviestack.roomdb.RoomDatabaseHelper
import com.example.moviestack.roomdb.mylist.MyListHelper
import com.example.moviestack.ui.common.ListType
import com.example.moviestack.ui.dashboard.mylists.mylistdetail.dialog.EditDialog
import com.example.moviestack.ui.dashboard.mylists.mylistdetail.dialog.EditDialogOnClick
import tyrantgit.explosionfield.ExplosionField
import java.lang.String

class MyListDetailAdapter(var context: Activity, val listMyList: List<MyList>,var type: ListType.Type): RecyclerView.Adapter<MyListDetailAdapter.MyListDetailViewHolder>() {

    var list = ArrayList<MyList>()
    init {
        list.addAll(listMyList)
    }

    private val viewBinderHelper = ViewBinderHelper()

    inner class MyListDetailViewHolder(val myListDataBinding : MyListDataBinding) : RecyclerView.ViewHolder(myListDataBinding.root) {
        fun bind(result: MyList,possion:Int) {
            this.myListDataBinding.mylist = result
            this.myListDataBinding.type = type
            this.myListDataBinding.executePendingBindings()
            viewBinderHelper.setOpenOnlyOne(true)
            viewBinderHelper.bind(myListDataBinding.swipelayout, String.valueOf(result.id))
            viewBinderHelper.closeLayout(String.valueOf(result.id))

            myListDataBinding.delete.setOnClickListener{
                MyListHelper(RoomDatabaseHelper().localeDataBase).deleteMyList(list.get(possion).id)
                var mExplosionField = ExplosionField.attach2Window(context);
                mExplosionField.explode(myListDataBinding.swipelayout);
                list.removeAt(possion);
                notifyItemRemoved(possion);
                notifyItemRangeChanged(possion, list.size);
            }

            myListDataBinding.edit.setOnClickListener{

                myListDataBinding.swipelayout.close(true)

                  var dialog = EditDialog(context)
                  dialog.setText(result.name)
                  dialog.setOnClickListener(object : EditDialogOnClick {
                      override fun onClick(s: kotlin.String) {
                          var mylist =  result
                          mylist.name = s
                          MyListHelper(RoomDatabaseHelper().localeDataBase).updateMyList(mylist)
                          myListDataBinding.textView13.text = s
                      }

                      override fun onCancel() {

                      }
                  })
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListDetailAdapter.MyListDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val similarDataBinding : MyListDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.my_list_detail,parent,false)
        return MyListDetailViewHolder(similarDataBinding)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: MyListDetailViewHolder, position: Int) {
        holder.bind(list[position],position)
    }

}