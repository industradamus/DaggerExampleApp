package com.example.daggerexampleapp.core.di.components

import com.example.daggerexampleapp.core.di.modules.CommonModule
import com.example.daggerexampleapp.core.di.modules.NetworkModule
import com.example.daggerexampleapp.core.di.modules.PresentersModule
import com.example.daggerexampleapp.ui.main.MainActivity
import dagger.Component

/**
 * @author s.buvaka
 */
@Component(
    modules = [
        NetworkModule::class,
        PresentersModule::class,
        CommonModule::class]
)
interface AppComponent {

    fun inject(activity: MainActivity)
}
