package com.example.data.network.apis

import com.example.data.network.models.responses.GetUsersResponse
import retrofit2.http.GET

interface UserListApi {
    @GET("users")
    suspend fun getUsers(): GetUsersResponse
}