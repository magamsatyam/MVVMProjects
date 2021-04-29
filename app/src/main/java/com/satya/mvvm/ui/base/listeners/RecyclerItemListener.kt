package com.satya.mvvm.ui.base.listeners

import com.satya.mvvm.model.AcronymsItem
import com.satya.mvvm.model.acronym.Lfs

interface RecyclerItemListener {
    fun onItemSelected(acronym : Lfs)
}