package com.techlads.sadapaytest.data.repository

import com.techlads.sadapaytest.data.entities.LatestRepositoryResponse
import com.techlads.sadapaytest.utils.Resource

class MainRepositoryTest : MainRepository {

    private lateinit var latestRepositoryResponse: LatestRepositoryResponse
    private var data : Resource<LatestRepositoryResponse>? = null

    var shouldReturnNetworkError = false

    private fun refreshData(){
        if (shouldReturnNetworkError){
            data = Resource.error("Testing error" , null)
        }else{
            data = Resource.success(latestRepositoryResponse)
        }
    }

    override suspend fun getRepos(page: Int): Resource<LatestRepositoryResponse> {
        refreshData()
        return data!!
    }
}