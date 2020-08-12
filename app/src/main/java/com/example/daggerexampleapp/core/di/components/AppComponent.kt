package com.example.daggerexampleapp.core.di.components

import com.example.daggerexampleapp.core.di.modules.NetworkModule
import com.example.daggerexampleapp.ui.main.MainActivity
import dagger.Component

/**
 * @author s.buvaka
 */
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
}
