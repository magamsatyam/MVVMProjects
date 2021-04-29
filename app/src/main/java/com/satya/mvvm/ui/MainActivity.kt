package com.satya.mvvm.ui

import com.satya.mvvm.model.acronym.AcronymItem
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.satya.mvvm.R
import com.satya.mvvm.data.Resource
import com.satya.mvvm.databinding.ActivityMainBinding
import com.satya.mvvm.ui.adapter.AcronymsAdapter
import com.satya.mvvm.ui.base.BaseActivity
import com.satya.mvvm.utils.toGone
import com.satya.mvvm.utils.toVisible
import com.satya.mvvm.utils.observe
import com.satya.mvvm.viewmodel.AcronymViewModel
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
        binding.lifecycleOwner = this
        binding.acronymViewModel = acronymViewModel
        val layoutManager = LinearLayoutManager(this)
        binding.rvAcronymList.layoutManager = layoutManager
        binding.rvAcronymList.setHasFixedSize(true)
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvAcronymList.toGone()
    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) View.GONE else View.VISIBLE
        binding.rvAcronymList.visibility = if (show) View.VISIBLE else View.GONE
        binding.pbLoading.toGone()
    }

    private fun getAcronymsList(status: Resource<ArrayList<AcronymItem>>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindListData(acronyms = it as ArrayList<AcronymItem>) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { acronymViewModel.showToastMessage(it) }
            }
        }
    }

    override fun observeViewModel() {
        observe(acronymViewModel.acronymData,::getAcronymsList)
    }

    private fun bindListData(acronyms: ArrayList<AcronymItem>) {
        if (!(acronyms.isNullOrEmpty())) {
            acronymsAdapter = AcronymsAdapter(acronymViewModel, acronyms[0].lfs)
            binding.rvAcronymList.adapter = acronymsAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

}