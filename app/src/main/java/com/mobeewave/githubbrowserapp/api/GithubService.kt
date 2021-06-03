package com.mobeewave.githubbrowserapp.api

import com.mobeewave.githubbrowserapp.data.SearchResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("search/repositories?sort=stars")
    suspend fun searchRepositories(
        @Query("q") q: String,
        @Query("page") page: Int
    ) : Response<SearchResponse>
}