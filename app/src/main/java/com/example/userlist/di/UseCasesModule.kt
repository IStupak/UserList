package com.example.userlist.di

import com.example.domain.userList.UserListUseCase
import com.example.domain.userList.UserListUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCasesModule {

    @Binds
    abstract fun bindsUserListUseCase(usersListUseCase: UserListUseCaseImpl): UserListUseCase

}