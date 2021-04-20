package com.satya.mvvm.ui.base.listeners

import com.satya.mvvm.model.AcronymsItem

interface RecyclerItemListener {
    fun onItemSelected(recipe : AcronymsItem)
}