package com.techlads.sadapaytest.framework.main

import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.techlads.sadapaytest.R
import com.techlads.sadapaytest.data.entities.LatestRepositoryResponse
import com.techlads.sadapaytest.framework.common.BaseActivity
import com.techlads.sadapaytest.utils.PER_PAGE
import com.techlads.sadapaytest.utils.PaginationScrollListener
import com.techlads.sadapaytest.utils.Resource
import com.techlads.sadapaytest.utils.visibility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animation_view.*
import kotlinx.android.synthetic.main.main_content.*

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main) {

    val viewModel : ReposViewModel by viewModels()
    lateinit var reposAdapter : ReposAdapter

    override fun initStuff() {
        setupAdapter()
        initialCall()
    }

    private fun initialCall() {
        reposRv.visibility(false)
        animView?.visibility(false)
        shimmerLl?.visibility(true)
        viewModel.getRepos(viewModel.currentPage)
    }

    private fun setupAdapter() {
        reposAdapter = ReposAdapter(
            arrayListOf(),
            getFirstVisiblePostion = { (reposRv.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() },
            getLastVisiblePostion = { (reposRv.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() })
        reposRv.adapter = reposAdapter
    }

    override fun listeners() {
        retryBtn?.setOnClickListener { initialCall() }
        swipeRL?.setOnRefreshListener {
            //Reset everything
            reposAdapter.values = arrayListOf()
            viewModel.currentPage = 1
            initialCall()
        }
        viewModel.content.observe(this, onReposResponse)
        reposRv?.addOnScrollListener(object : PaginationScrollListener(reposRv?.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                viewModel.isLoading = true
                viewModel.currentPage++
                reposAdapter.addLoadingFooter()
                viewModel.getRepos(viewModel.currentPage)
            }

            override fun getTotalPageCount(): Int {
                return viewModel.totalPageCount
            }

            override fun isLastPage(): Boolean {
                return (viewModel.currentPage * PER_PAGE) >= viewModel.totalPageCount
            }

            override fun isLoading(): Boolean {
                return viewModel.isLoading
            }

        })
    }

    override fun getToolbar(): Toolbar? {
        return null
    }

    private val onReposResponse : Observer<Resource<LatestRepositoryResponse>> = Observer {

        swipeRL?.isRefreshing = false
        viewModel.isLoading = false
        reposAdapter.removeLoadingFooter()

        when (it?.status) {
            Resource.Status.SUCCESS -> {

                reposRv.visibility(true)
                animView?.visibility(false)
                shimmerLl?.visibility(false)

                it.data?.let { sh ->
                    viewModel.totalPageCount = (sh.total_count / PER_PAGE)
                    reposAdapter.update(sh.repos)
                }
            }

            Resource.Status.ERROR -> {

                reposRv.visibility(false)
                animView?.visibility(true)
                shimmerLl?.visibility(false)
            }

            Resource.Status.LOADING -> {

                reposRv.visibility(false)
                animView?.visibility(false)
                shimmerLl?.visibility(true)
            }
        }
    }

}
