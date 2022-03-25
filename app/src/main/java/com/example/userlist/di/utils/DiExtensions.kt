package com.example.userlist.di.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.userlist.di.ApplicationComponent
import com.example.userlist.utils.App

fun Fragment.getAppComponent(): ApplicationComponent =
    (requireContext().applicationContext as App).appComponent

inline fun <reified T : ViewModel> Fragment.lazyViewModel(
    noinline create: () -> T
) = viewModels<T> {
    ViewModelWithSaveStateHandleFactory(this, create)
}