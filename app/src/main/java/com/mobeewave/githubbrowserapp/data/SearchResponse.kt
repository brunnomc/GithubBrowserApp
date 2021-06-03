package com.mobeewave.githubbrowserapp.data

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val items: List<Repository>
) {
    override fun toString(): String {
        return "totalCount: $totalCount"
    }
}


