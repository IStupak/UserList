package com.example.data.network.repositories

import com.example.data.mappers.databaseMappers.UserNetworkToDomainModel
import com.example.data.network.apis.UserListApi
import com.example.domain.models.UserModel
import com.example.domain.userList.UserListNetworkRepository
import javax.inject.Inject

class UserListNetworkRepositoryImpl @Inject constructor(
    private val usersListApi: UserListApi,
    private val userNetworkToDomainModel: UserNetworkToDomainModel
) : UserListNetworkRepository {

    override suspend fun getUsers(): List<UserModel> {
        return usersListApi.getUsers().usersList.map { userNetworkToDomainModel.convert(it) }
    }
}