package com.techlads.sadapaytest.data.entities

import com.squareup.moshi.Json


/**
 *
 * Kotlin
 *
 * @author Umair Khalid (umair.khalid786@outlook.com)
 * @package com.techlads.sadapaytest.data.entities
 */

data class LatestRepositoryResponse(@field:Json(name = "items") val repos : List<Repo>,
                                    val total_count: Int) {
    data class Repo(val name: String,
                    val full_name: String,
                    val description : String,
                    val language : String,
                    val stargazers_count : Int,
                    val owner: Owner
                    ) {

        data class Owner(val avatar_url: String, val login : String)
    }
}