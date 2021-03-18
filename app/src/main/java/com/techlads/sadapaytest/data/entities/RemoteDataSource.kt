package com.techlads.sadapaytest.data.entities

import com.techlads.sadapaytest.data.api.GithubApi
import javax.inject.Inject


/**
 *
 * Kotlin
 *
 * @author Umair Khalid (umair.khalid786@outlook.com)
 * @package com.techlads.sadapaytest.data.entities
 */

class RemoteDataSource @Inject constructor(
    private val api : GithubApi
) : BaseDataSource() {

    suspend fun getRepos(page : Int) = getResult { api.getRepos(page) }
}