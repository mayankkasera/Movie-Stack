package com.example.moviestack.ui.moviedetail.info.dialog

import android.app.Dialog
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviestack.R
import com.example.moviestack.pojo.MovieInfo
import com.example.moviestack.pojo.MyList
import com.example.moviestack.pojo.MyListDetail
import com.example.moviestack.ui.moviedetail.info.dialog.adapter.MyListAdapter
import kotlinx.android.synthetic.main.my_list_detail_dialog.*

class MyListDetailDialog(var context: Context,var type : MyListDetail.Type) {
    private lateinit var dialog: Dialog

    init{
        dialog = Dialog(context)
        dialog.setContentView(R.layout.my_list_detail_dialog)
        dialog.setCancelable(false)
        dialog.show()
    }

    fun dismiss(){
        dialog.dismiss()
    }

    fun show(){
        dialog.show()
    }

    fun setAdapter(list : List<MyList>,movieInfo :MovieInfo){
        dialog.recyclerview.layoutManager = LinearLayoutManager(context)
        dialog.recyclerview.adapter = MyListAdapter(movieInfo,list,type)
    }

    fun setOnclic(myListDetailDialogOnlick : MyListDetailDialogOnlick){
        dialog.add.setOnClickListener{
            dismiss()
            myListDetailDialogOnlick.onClick()
        }

        dialog.ok.setOnClickListener{
            dismiss()
        }
    }

}