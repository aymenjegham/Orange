package com.aymenjegham.orange.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aymenjegham.core.domain.account.Account
import com.aymenjegham.orange.databinding.ItemAccountListBinding
import com.aymenjegham.orange.global.utils.wrapAction

class AccountListHolder private constructor(
    private val binding: ItemAccountListBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Account, isSelected: Boolean, itemClickAction: ((Int) -> Unit)?) {
        binding.account = item
        binding.isSelected = isSelected
        binding.itemClickAction = wrapAction { itemClickAction?.invoke(adapterPosition) }
    }

    companion object {

        fun create(parent: ViewGroup) = LayoutInflater
            .from(parent.context)
            .let { ItemAccountListBinding.inflate(it, parent, false) }
            .let {
                AccountListHolder(
                    it
                )
            }
    }
}