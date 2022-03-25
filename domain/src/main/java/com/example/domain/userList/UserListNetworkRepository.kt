package com.example.domain.userList

import com.example.domain.models.UserModel

interface UserListNetworkRepository {
    suspend fun getUsers(): List<UserModel>
}