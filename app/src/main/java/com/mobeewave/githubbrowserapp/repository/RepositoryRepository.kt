package com.mobeewave.githubbrowserapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mobeewave.githubbrowserapp.api.GithubService
import com.mobeewave.githubbrowserapp.data.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryRepository @Inject constructor(
    private val githubService: GithubService
) {

    fun searchRepositories(language: String) : Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { RepositoryPageSource(githubService, language) }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 50
    }
}

