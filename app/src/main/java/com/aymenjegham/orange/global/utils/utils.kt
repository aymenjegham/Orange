package com.aymenjegham.orange.global.utils

import android.view.View
import com.aymenjegham.orange.global.helper.OnClickWrapper

fun wrapAction(action: (() -> Unit)): OnClickWrapper = object : OnClickWrapper {
    override fun onClick(view: View) {
        action()
    }
}