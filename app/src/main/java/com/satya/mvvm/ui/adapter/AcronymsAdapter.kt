package com.satya.mvvm.ui.adapter

import android.util.Log
import com.satya.mvvm.model.acronym.Lfs
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.satya.mvvm.databinding.AcronymItemBinding
import com.satya.mvvm.model.AcronymsItem
import com.satya.mvvm.viewmodel.AcronymViewModel
import com.satya.mvvm.ui.base.listeners.RecyclerItemListener

class AcronymsAdapter(private val acronymViewModel: AcronymViewModel, private val acronyms: List<Lfs>) : RecyclerView.Adapter<AcronymsViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(acronym: Lfs) {
            Log.d("onItemSelected()",acronym.lf)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymsViewHolder {
        val itemBinding = AcronymItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AcronymsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AcronymsViewHolder, position: Int) {
        holder.bind(acronyms[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return acronyms.size
    }
}