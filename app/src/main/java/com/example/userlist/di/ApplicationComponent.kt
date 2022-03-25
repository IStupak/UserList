package com.example.userlist.di

import android.content.Context
import com.example.userlist.features.userDetails.UserDetailsViewModel
import com.example.userlist.features.userList.UserListViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        UseCasesModule::class,
        NetworkModule::class,
        DatabaseModule::class
    ]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun userListViewModel(): UserListViewModel.Factory
    fun userDetailsViewModel(): UserDetailsViewModel.Factory

}
