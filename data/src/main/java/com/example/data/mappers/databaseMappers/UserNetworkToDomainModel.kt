package com.example.data.mappers.databaseMappers

import com.example.data.mappers.Mapper
import com.example.data.network.models.UserModelNetwork
import com.example.domain.models.UserModel
import javax.inject.Inject

class UserNetworkToDomainModel @Inject constructor() : Mapper<UserModelNetwork, UserModel> {
    override fun convert(data: UserModelNetwork): UserModel {
        return UserModel(
            id = data.id,
            emailt = data.emailt,
            firstName = data.firstName,
            lastName = data.lastName,
            avatar = data.avatar
        )
    }
}