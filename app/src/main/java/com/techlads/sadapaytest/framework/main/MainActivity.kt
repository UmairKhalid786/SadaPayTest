package com.techlads.sadapaytest.framework.main

import android.app.Activity
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.techlads.sadapaytest.R
import com.techlads.sadapaytest.data.entities.LatestRepositoryResponse
import com.techlads.sadapaytest.framework.common.BaseActivity
import com.techlads.sadapaytest.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main) {

    val viewModel : ReposViewModel by viewModels()
    lateinit var reposAdapter : ReposAdapter

    override fun initStuff() {
        setupAdapter()
        viewModel.getRepos(1)
    }

    private fun setupAdapter() {
        reposAdapter = ReposAdapter(
            listOf(),
            getFirstVisiblePostion = { (reposRv.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() },
            getLastVisiblePostion = { (reposRv.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() })
        reposRv.adapter = reposAdapter
    }

    override fun listeners() {
        viewModel.content.observe(this, onReposResponse)
    }

    override fun getToolbar(): Toolbar? {
        return null
    }

    private val onReposResponse : Observer<Resource<LatestRepositoryResponse>> = Observer {
        when (it?.status) {
            Resource.Status.SUCCESS -> {

                Toast.makeText(this, it.data?.total_count.toString(), Toast.LENGTH_LONG).show()

                it.data?.let { it1 -> reposAdapter.update(it1.repos) }
//                if (!it.data?.photos?.photos.isNullOrEmpty()) (searchResultRv.adapter as PhotosAdapter).update(
//                    it.data?.photos?.photos!!
//                )
            }

            Resource.Status.ERROR -> {
//                toggleData(false)
//                it.message?.let { it1 -> activity?.showMessage(it1) }
            }

            Resource.Status.LOADING -> {
//                toggleData(true)
            }
        }
    }
}
