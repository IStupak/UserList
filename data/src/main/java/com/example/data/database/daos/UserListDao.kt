package com.example.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.models.UserModelDb

@Dao
interface UserListDao {
    @Query("SELECT * FROM UserModelDb")
    suspend fun getUserList(): List<UserModelDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserList(users: List<UserModelDb>)
}