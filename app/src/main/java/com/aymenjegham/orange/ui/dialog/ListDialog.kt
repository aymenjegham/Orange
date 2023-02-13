package com.aymenjegham.orange.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.ObservableBoolean
import androidx.recyclerview.widget.RecyclerView
import com.aymenjegham.orange.databinding.DialogListBinding
import com.aymenjegham.orange.global.extention.wrapAction

class ListDialog<VH : RecyclerView.ViewHolder, T>(
    context: Context,
    private val title: String,
    private val adapter: RecyclerView.Adapter<VH>,
    private val isSelected: ObservableBoolean,
    private val list: List<T>,
    private val clickAction: (() -> Unit)? = null,
) : AlertDialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DialogListBinding
            .inflate(layoutInflater, null, false)
            .also(::bind)


        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        setCanceledOnTouchOutside(false)

    }


    private fun bind(binding: DialogListBinding) {
        setContentView(binding.root)
        binding.title = title
        binding.list = list
        binding.isEnabled = isSelected
        binding.adapter = adapter
        binding.clickWrapper = wrapAction(clickAction)
        binding.confirmVisibility = clickAction != null
    }
}