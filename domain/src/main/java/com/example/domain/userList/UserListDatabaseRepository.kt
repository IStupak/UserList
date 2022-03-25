package com.example.domain.userList

import com.example.domain.models.UserModel

interface UserListDatabaseRepository {
    suspend fun getUsers(): List<UserModel>
    suspend fun insertUsers(users: List<UserModel>)
}