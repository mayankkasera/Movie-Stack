package com.codeinger.moviestack.ui.moviedetail.info.dialog

import android.app.Dialog
import android.content.Context
import com.codeinger.moviestack.R
import kotlinx.android.synthetic.main.my_list.*

class MyListDialog (var context: Context) {
    private lateinit var dialog: Dialog

    init{
        dialog = Dialog(context)
        dialog.setContentView(R.layout.my_list)
        dialog.setCancelable(false)
        dialog.show()
    }

    fun dismiss(){
        dialog.dismiss()
    }

    fun show(){
        dialog.show()
    }

    fun setOnClickListener(onclick: MyListDialogOnclick){
        dialog.ok.setOnClickListener{
            dismiss()
            onclick.onClick(dialog.text.text.toString())
        }
        dialog.cancel.setOnClickListener{
            dismiss()
            onclick.onCancel()
        }
    }
}