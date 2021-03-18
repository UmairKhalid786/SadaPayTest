package com.techlads.sadapaytest.data.api

import com.techlads.sadapaytest.data.entities.LatestRepositoryResponse
import com.techlads.sadapaytest.utils.PER_PAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/**
 *
 * Kotlin
 *
 * @author Umair Khalid (umair.khalid786@outlook.com)
 * @package com.techlads.sadapaytest.data.api
 */


interface GithubApi {
    @GET("search/repositories?q=language=+sort:stars")
    suspend fun getRepos(@Query("page")  page : Int = 1,
                        @Query("per_page")  perPage : Int = PER_PAGE) : Response<LatestRepositoryResponse>
}