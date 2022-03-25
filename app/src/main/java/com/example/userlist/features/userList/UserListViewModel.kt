package com.example.userlist.features.userList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.UserModel
import com.example.domain.userList.UserListUseCase
import com.example.domain.utils.ResultOf
import com.example.userlist.R
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserListViewModel @AssistedInject constructor(
    private val userListUseCase: UserListUseCase,
) : ViewModel() {

    private val _userList =
        MutableStateFlow<ResultOf<List<UserModel>>>(ResultOf.Loading())
    val userList = _userList.asStateFlow()

    private val _openUserDetails = MutableSharedFlow<UserModel>()
    val openUserDetails = _openUserDetails.asSharedFlow()

    private val _showMessage = MutableSharedFlow<Int>()
    val showMessage = _showMessage.asSharedFlow()

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            _userList.emit(ResultOf.Loading())
            val result = userListUseCase.getUsers()
            if (result is ResultOf.Success && result.fromCache) {
                _showMessage.emit(R.string.loading_from_network_error)
            }
            _userList.emit(result)
        }
    }

    fun openUserDetails(userModel: UserModel) {
        viewModelScope.launch {
            _openUserDetails.emit(userModel)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(): UserListViewModel
    }
}