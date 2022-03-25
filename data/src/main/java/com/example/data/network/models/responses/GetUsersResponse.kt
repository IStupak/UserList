package com.example.data.network.models.responses

import com.example.data.network.models.UserModelNetwork
import com.google.gson.annotations.SerializedName

data class GetUsersResponse(
    @SerializedName("data") val usersList: List<UserModelNetwork>
)