package com.example.daggerexampleapp.core.application

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho

/**
 * @author s.buvaka
 */
class App : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        initStetho()
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    companion object {

        lateinit var instance: App

        fun applicationContext(): Context = instance.applicationContext
    }
}
