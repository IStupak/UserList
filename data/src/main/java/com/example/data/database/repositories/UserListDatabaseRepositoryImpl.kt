package com.example.data.database.repositories

import com.example.data.database.daos.UserListDao
import com.example.data.mappers.networkMappers.UserDbModelToDomainModel
import com.example.domain.models.UserModel
import com.example.domain.userList.UserListDatabaseRepository
import javax.inject.Inject

class UserListDatabaseRepositoryImpl @Inject constructor(
    private val userListDao: UserListDao,
    private val userDbModelToDomainModel: UserDbModelToDomainModel
) : UserListDatabaseRepository {
    override suspend fun getUsers(): List<UserModel> {
        return userListDao.getUserList().map { userDbModelToDomainModel.convert(it) }
    }

    override suspend fun insertUsers(users: List<UserModel>) {
        userListDao.addUserList(users.map { userDbModelToDomainModel.revert(it) })
    }
}