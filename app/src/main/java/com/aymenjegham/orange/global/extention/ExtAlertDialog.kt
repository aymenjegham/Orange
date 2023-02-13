package com.aymenjegham.orange.global.extention

import android.app.AlertDialog
import android.view.View
import com.aymenjegham.orange.global.helper.OnClickWrapper

fun AlertDialog.wrapAction(action: (() -> Unit)?, isDismissible:Boolean=true): OnClickWrapper = object : OnClickWrapper {
    override fun onClick(view: View) {
        if(isDismissible){
            dismiss()
        }
        action?.invoke()
    }
}

