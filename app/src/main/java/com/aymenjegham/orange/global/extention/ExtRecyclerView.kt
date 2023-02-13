package com.aymenjegham.orange.global.extention

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("listAdapterData")
fun <T> submitList(recyclerView: RecyclerView, list: List<T>?) {
    if (list != null && recyclerView.adapter is ListAdapter<*, *>) {
        (recyclerView.adapter as? ListAdapter<T, RecyclerView.ViewHolder>)?.submitList(list.toMutableList())
    }
}