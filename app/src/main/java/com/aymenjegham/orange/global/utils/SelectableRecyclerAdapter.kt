package com.aymenjegham.orange.global.utils


const val NO_SELECTION = -1

const val INITIAL_SELECTED = 0

interface SelectableRecyclerAdapter {

    var selectedItemPosition: Int

    val initialSelectionIndex : Int?

    val selectedItemsPositions: MutableList<Int>

    val selectionMode: SelectionMode

    fun onItemClicked(position: Int) =
        if (selectionMode == SelectionMode.MULTIPLE_SELECTION) {
            setSelectedMultiple(position)
        } else {
            setSelected(position)
        }


    private fun setSelectedMultiple(it: Int) {
        if (isItemSelected(it)) {
            selectedItemsPositions -= it
        } else {
            selectedItemsPositions += it
        }
        notifyItemChanged(it)
    }

    fun isItemSelected(position: Int): Boolean =
        if (selectionMode == SelectionMode.MULTIPLE_SELECTION) {
            selectedItemsPositions.contains(position)
        } else {
            selectedItemPosition == position
        }


    fun getSelectedItemsPositionsList() = selectedItemsPositions.toList()


    fun restSelection() {
        when (selectionMode) {
            SelectionMode.SINGLE_SELECTION -> INITIAL_SELECTED
            SelectionMode.SINGLE_SELECTION_DESELECT -> selectedItemPosition =
                NO_SELECTION
            else -> selectedItemsPositions.clear()
        }
    }

    /**
     * The function will do nothing if  @param selectionMode == SelectionMode.SINGLE_SELECTION
     * and @param position < 0
     * */
    fun select(position: Int) {
        if (
            (position >= 0 && selectionMode == SelectionMode.SINGLE_SELECTION)
            || selectionMode != SelectionMode.SINGLE_SELECTION
        ) {
            setSelected(position)
        }
    }

    private fun setSelected(position: Int) {
        if (position == selectedItemPosition && selectionMode == SelectionMode.SINGLE_SELECTION_DESELECT)
            selectedItemPosition = NO_SELECTION
        else {
            val oldSelected = selectedItemPosition
            selectedItemPosition = position
            notifyItemChanged(oldSelected)
        }
        notifyItemChanged(position)
    }

    fun notifyItemChanged(position: Int)


    enum class SelectionMode {

        /**
         *  Only one item is selected, First item will be initially selected
         *  can not deselect item, an item will always be selected
         */
        SINGLE_SELECTION,

        /**
         * Can select multiple item
         */
        MULTIPLE_SELECTION,

        /**
         * Only one item is selected
         *  can deselect item, list may not have any item selected
         * */
        SINGLE_SELECTION_DESELECT
    }

    companion object {
        fun computeInitialSelection(selectionMode: SelectionMode, initialSelectionIndex: Int?) =
            if (selectionMode == SelectionMode.SINGLE_SELECTION)
                initialSelectionIndex ?: INITIAL_SELECTED
            else
                NO_SELECTION
    }

}