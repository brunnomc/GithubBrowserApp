package com.mobeewave.githubbrowserapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobeewave.githubbrowserapp.data.Repository
import com.mobeewave.githubbrowserapp.repository.RepositoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MainViewModel @Inject constructor(private val repositoryRepository: RepositoryRepository): ViewModel() {


    private var searchResult: Flow<PagingData<Repository>>? = null
    private var lastLanguageSearched: String? = null

    fun searchRepositories(language: String): Flow<PagingData<Repository>> {
        if (language == lastLanguageSearched && searchResult != null){
            return searchResult!!
        }
        lastLanguageSearched = language
        searchResult = repositoryRepository.searchRepositories(language).cachedIn(viewModelScope)
        return searchResult!!
    }
}


