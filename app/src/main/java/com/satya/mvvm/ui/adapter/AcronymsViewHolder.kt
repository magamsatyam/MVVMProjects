package com.satya.mvvm.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.satya.mvvm.databinding.AcronymItemBinding
import com.satya.mvvm.model.LongForm
import com.satya.mvvm.ui.base.listeners.RecyclerItemListener

class AcronymsViewHolder(private val itemBinding: AcronymItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(acronymsItem: LongForm, recyclerItemListener: RecyclerItemListener) {
        itemBinding.tvAcronym.text = acronymsItem.lf
//        itemBinding.rlRecipeItem.setOnClickListener { recyclerItemListener.onItemSelected(recipesItem) }
    }
}
