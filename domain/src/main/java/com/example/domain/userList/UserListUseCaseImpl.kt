package com.example.domain.userList

import com.example.domain.models.UserModel
import com.example.domain.utils.ResultOf
import com.example.domain.utils.loadAndCache
import javax.inject.Inject

interface UserListUseCase {
    suspend fun getUsers(): ResultOf<List<UserModel>>
}

class UserListUseCaseImpl @Inject constructor(
    private val networkRepo: UserListNetworkRepository,
    private val dbRepo: UserListDatabaseRepository
) : UserListUseCase {
    override suspend fun getUsers(): ResultOf<List<UserModel>> {
        return loadAndCache(
            loadFromNetwork = { networkRepo.getUsers() },
            loadFromDatabase = { dbRepo.getUsers() },
            updateItemsInDatabase = { dbRepo.insertUsers(it) }
        )
    }
}