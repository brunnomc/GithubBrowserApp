package com.mobeewave.githubbrowserapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mobeewave.githubbrowserapp.data.SearchResponse
import com.mobeewave.githubbrowserapp.repository.RepositoryRepository
import retrofit2.Response
import javax.inject.Inject


class MainViewModel @Inject constructor(private val repositoryRepository: RepositoryRepository): ViewModel() {


    fun searchRepositories(q: String, page: Int): LiveData<Response<SearchResponse>?> {
        return repositoryRepository.searchRepositories(q, page)
    }
}


