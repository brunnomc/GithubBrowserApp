package com.mobeewave.githubbrowserapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobeewave.githubbrowserapp.api.GithubService
import com.mobeewave.githubbrowserapp.data.SearchResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class RepositoryRepository @Inject constructor(
    private val githubService: GithubService
) {

    fun searchRepositories(q: String, page: Int): LiveData<Response<SearchResponse>?> {
        val responseLiveData = MutableLiveData<Response<SearchResponse>?>()

        CoroutineScope(Dispatchers.IO).launch {
            responseLiveData.postValue(githubService.searchRepositories(q, page))
        }
        return responseLiveData
    }
}

