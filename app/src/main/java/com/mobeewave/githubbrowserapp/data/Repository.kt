package com.mobeewave.githubbrowserapp.data

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("owner") val owner: Owner,
    @SerializedName("forks") val forks: Int,
    @SerializedName("stargazers_count") val stargazersCount: Int
) {
    override fun toString(): String {
        return "\nid: $id" +
                "\nname: $name" +
                "\nowner: $owner" +
                "\nforks: $forks" +
                "\nstargazersCount: $stargazersCount"
    }
}
