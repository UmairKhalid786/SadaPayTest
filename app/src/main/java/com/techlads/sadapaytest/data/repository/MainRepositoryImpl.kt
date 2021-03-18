package com.techlads.sadapaytest.data.repository

import com.techlads.sadapaytest.data.entities.LatestRepositoryResponse
import com.techlads.sadapaytest.data.entities.RemoteDataSource
import com.techlads.sadapaytest.utils.Resource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: RemoteDataSource
) : MainRepository {

    override suspend fun getRepos(page: Int) : Resource<LatestRepositoryResponse> = api.getRepos(page)
}