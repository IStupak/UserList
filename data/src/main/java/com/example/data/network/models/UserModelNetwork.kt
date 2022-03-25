package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class UserModelNetwork(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val emailt: String?,
    @SerializedName("first_name") val firstName: String?,
    @SerializedName("last_name") val lastName: String?,
    @SerializedName("avatar") val avatar: String?
)