package com.example.data.mappers.networkMappers

import com.example.data.database.models.UserModelDb
import com.example.data.mappers.MapperRevert
import com.example.domain.models.UserModel
import javax.inject.Inject

class UserDbModelToDomainModel @Inject constructor() : MapperRevert<UserModelDb, UserModel> {
    override fun convert(data: UserModelDb): UserModel {
        return UserModel(
            id = data.id,
            emailt = data.emailt,
            firstName = data.firstName,
            lastName = data.lastName,
            avatar = data.avatar
        )
    }

    override fun revert(data: UserModel): UserModelDb {
        return UserModelDb(
            id = data.id,
            emailt = data.emailt,
            firstName = data.firstName,
            lastName = data.lastName,
            avatar = data.avatar
        )
    }
}