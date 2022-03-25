package com.example.userlist.features.userDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.domain.models.UserModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserDetailsViewModel @AssistedInject constructor(@Assisted savedStateHandle: SavedStateHandle) :
    ViewModel() {

    private val _userModel = MutableStateFlow(
        savedStateHandle.get<UserModel>("user_model")
            ?: throw Exception("User model must be not-null")
    )
    val userModel = _userModel.asStateFlow()

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): UserDetailsViewModel
    }
}
