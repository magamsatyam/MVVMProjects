package com.satya.mvvm.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.satya.mvvm.R
import com.satya.mvvm.data.Resource
import com.satya.mvvm.databinding.ActivityMainBinding
import com.satya.mvvm.model.Acronyms
import com.satya.mvvm.ui.adapter.AcronymsAdapter
import com.satya.mvvm.ui.base.BaseActivity
import com.task.utils.observe
import com.satya.mvvm.utils.toGone
import com.satya.mvvm.utils.toVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val acronymViewModel: AcronymViewModel by viewModels()
    private lateinit var acronymsAdapter: AcronymsAdapter

    override fun initViewBinding() {
         binding = ActivityMainBinding.inflate(layoutInflater)
         val view = binding.root
         setContentView(view)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.acronyms)
        val layoutManager = LinearLayoutManager(this)
        binding.rvRecipesList.layoutManager = layoutManager
        binding.rvRecipesList.setHasFixedSize(true)

    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvRecipesList.toGone()
    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) View.GONE else View.VISIBLE
        binding.rvRecipesList.visibility = if (show) View.VISIBLE else View.GONE
        binding.pbLoading.toGone()
    }

    private fun getAcronymsList(status: Resource<Acronyms>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindListData(acronyms = it) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { acronymViewModel.showToastMessage(it) }
            }
        }
    }

    override fun observeViewModel() {
        observe(acronymViewModel.acronymData, ::getAcronymsList)
    }

    private fun bindListData(acronyms: Acronyms) {
        if (!(acronyms.isNullOrEmpty())) {
            acronymsAdapter = AcronymsAdapter(acronymViewModel, acronyms.get(0))
            binding.rvRecipesList.adapter = acronymsAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

}