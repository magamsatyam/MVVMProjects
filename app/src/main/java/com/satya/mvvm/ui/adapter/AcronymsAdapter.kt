package com.satya.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.satya.mvvm.databinding.AcronymItemBinding
import com.satya.mvvm.model.AcronymsItem
import com.satya.mvvm.ui.AcronymViewModel
import com.satya.mvvm.ui.base.listeners.RecyclerItemListener

class AcronymsAdapter(private val acronymViewModel: AcronymViewModel, private val acronyms: AcronymsItem) : RecyclerView.Adapter<AcronymsViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(recipe: AcronymsItem) {
//            acronymViewModel.openRecipeDetails(recipe)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymsViewHolder {
        val itemBinding = AcronymItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AcronymsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AcronymsViewHolder, position: Int) {
        holder.bind(acronyms.lfs[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return acronyms.lfs.size
    }
}