package com.example.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val id: Int,
    val emailt: String?,
    val firstName: String?,
    val lastName: String?,
    val avatar: String?
) : Parcelable