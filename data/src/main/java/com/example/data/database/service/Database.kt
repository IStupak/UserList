package com.example.data.database.service

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.daos.UserListDao
import com.example.data.database.models.UserModelDb

@Database(entities = [UserModelDb::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userListDao: UserListDao

    companion object {
        const val DATABASE_NAME = "database"
    }
}