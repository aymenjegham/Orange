package com.aymenjegham.orange.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.aymenjegham.core.domain.account.Account
import com.aymenjegham.orange.global.utils.SelectableRecyclerAdapter
import com.aymenjegham.orange.global.utils.SelectableRecyclerAdapter.Companion.computeInitialSelection

class AccountListAdapter(

    indexInitial: Int,
    private val selectAction: (Int) -> Unit,
) : ListAdapter<Account, AccountListHolder>(DIFF_CALLBACK), SelectableRecyclerAdapter {

    override val selectionMode: SelectableRecyclerAdapter.SelectionMode =
        SelectableRecyclerAdapter.SelectionMode.SINGLE_SELECTION
    override val initialSelectionIndex: Int = indexInitial
    override var selectedItemPosition: Int =
        computeInitialSelection(selectionMode, initialSelectionIndex)
    override val selectedItemsPositions: MutableList<Int> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AccountListHolder.create(
            parent
        )

    override fun onBindViewHolder(holder: AccountListHolder, position: Int) {
        holder.bind(getItem(position), isItemSelected(position)) {
            onItemClicked(it)
            selectAction(it)
        }
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Account>() {

            override fun areItemsTheSame(oldItem: Account, newItem: Account) =
                oldItem.AccountId == newItem.AccountId

            override fun areContentsTheSame(oldItem: Account, newItem: Account) =
                oldItem != newItem

        }

    }


}