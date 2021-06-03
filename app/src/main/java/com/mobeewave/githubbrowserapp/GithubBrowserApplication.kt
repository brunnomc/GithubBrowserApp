package com.mobeewave.githubbrowserapp

import android.app.Application
import com.mobeewave.githubbrowserapp.di.AppComponent
import com.mobeewave.githubbrowserapp.di.DaggerAppComponent


class GithubBrowserApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}