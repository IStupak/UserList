package com.example.userlist.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.daos.UserListDao
import com.example.data.database.repositories.UserListDatabaseRepositoryImpl
import com.example.data.database.service.Database
import com.example.data.database.service.Database.Companion.DATABASE_NAME
import com.example.domain.userList.UserListDatabaseRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule.DaoModule::class, DatabaseModule.RepositoryBindsModule::class])
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, DATABASE_NAME).build()
    }

    @Module
    class DaoModule {
        @Provides
        fun provideUserListDao(database: Database): UserListDao = database.userListDao
    }

    @Module
    interface RepositoryBindsModule {
        @Binds
        fun bindsUserListDatabaseRepository(usersRepositoryImpl: UserListDatabaseRepositoryImpl): UserListDatabaseRepository
    }
}