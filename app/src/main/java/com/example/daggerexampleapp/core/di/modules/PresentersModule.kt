package com.example.daggerexampleapp.core.di.modules

import com.example.daggerexampleapp.ui.main.MainScreenPresenter
import com.example.daggerexampleapp.ui.main.MainScreenPresenterImpl
import dagger.Binds
import dagger.Module

/**
 * @author s.buvaka
 */
@Module
interface PresentersModule {

    @Binds
    fun bindMainScreenPresenter(presenter: MainScreenPresenterImpl): MainScreenPresenter
}
