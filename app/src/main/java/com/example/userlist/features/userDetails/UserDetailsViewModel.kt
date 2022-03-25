package com.example.userlist.features.userDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.domain.models.UserModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow

class UserDetailsViewModel @AssistedInject constructor(@Assisted savedStateHandle: SavedStateHandle) :
    ViewModel() {
    val usermodel = MutableStateFlow(
        savedStateHandle.get<UserModel>("user_model")
            ?: throw Exception("User model must be not-null")
    )

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): UserDetailsViewModel
    }

}
