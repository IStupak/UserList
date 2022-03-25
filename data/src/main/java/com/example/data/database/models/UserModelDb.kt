package com.example.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserModelDb(
    @PrimaryKey val id: Int,
    val emailt: String?,
    val firstName: String?,
    val lastName: String?,
    val avatar: String?
)