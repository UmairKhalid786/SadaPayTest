package com.techlads.sadapaytest.framework.main

import androidx.lifecycle.*
import com.techlads.sadapaytest.data.entities.LatestRepositoryResponse
import com.techlads.sadapaytest.data.repository.MainRepository
import com.techlads.sadapaytest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 * Kotlin
 *
 * @author Umair Khalid (umair.khalid786@outlook.com)
 * @package com.techlads.sadapaytest.framework.main
 */
@HiltViewModel
class ReposViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: MainRepository
) : ViewModel() {

    var totalPageCount = 1
    var currentPage = 1

    var isLoading: Boolean = false

    private val _content = MutableLiveData<Resource<LatestRepositoryResponse>>()
    val content: LiveData<Resource<LatestRepositoryResponse>> = _content

    fun getRepos(page : Int) {
        viewModelScope.launch {
            val content = withContext(Dispatchers.IO){
                useCase.getRepos(page)
            }

            _content.value = content
        }
    }
}