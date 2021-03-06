package com.mobeewave.githubbrowserapp.data

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("id") val id: String,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
)