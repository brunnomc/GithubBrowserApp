package com.mobeewave.githubbrowserapp.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mobeewave.githubbrowserapp.api.GithubService
import com.mobeewave.githubbrowserapp.api.LANGUAGE_QUERY
import com.mobeewave.githubbrowserapp.data.Repository
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_INDEX = 1

class RepositoryPageSource(
    private val githubService: GithubService,
    private val language: String
) : PagingSource<Int, Repository>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        return try {
            val currentPage = params.key ?: STARTING_PAGE_INDEX
            val repositories =
                githubService.searchRepositories(LANGUAGE_QUERY + language, currentPage, params.loadSize).items
            val nextPage = if (repositories.isEmpty()) null else currentPage + 1

            LoadResult.Page(
                data = repositories,
                prevKey = if (currentPage == STARTING_PAGE_INDEX) null else currentPage - 1,
                nextKey = nextPage
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}