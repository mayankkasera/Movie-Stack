package com.codeinger.moviestack.ui.dashboard.mylists.mylistdetail.dialog

import android.app.Dialog
import android.content.Context
import com.codeinger.moviestack.R
import kotlinx.android.synthetic.main.my_list.cancel
import kotlinx.android.synthetic.main.my_list.ok
import kotlinx.android.synthetic.main.my_list.text

class EditDialog (var context: Context) {
    private lateinit var dialog: Dialog
    init{
        dialog = Dialog(context)
        dialog.setContentView(R.layout.edit_dialog)
        dialog.setCancelable(false)
        dialog.show()
    }

    fun dismiss(){
        dialog.dismiss()
    }

    fun show(){
        dialog.show()
    }

    fun setText(text : String){
        dialog.text.setText(text)
    }

    fun setOnClickListener(onclick: EditDialogOnClick){
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