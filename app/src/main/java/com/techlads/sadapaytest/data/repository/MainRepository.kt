package com.techlads.sadapaytest.data.repository

import com.techlads.sadapaytest.data.entities.LatestRepositoryResponse
import com.techlads.sadapaytest.utils.Resource


/**
 *
 * Kotlin
 *
 * @author Umair Khalid (umair.khalid786@outlook.com)
 * @package com.techlads.sadapaytest.data.repository
 */


interface MainRepository {
    suspend fun getRepos(page: Int): Resource<LatestRepositoryResponse>
}