package com.example.userlist.utils

import android.app.Application
import com.example.userlist.di.ApplicationComponent
import com.example.userlist.di.DaggerApplicationComponent

class App : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.factory().create(this)
    }
}