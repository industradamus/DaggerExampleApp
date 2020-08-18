package com.example.daggerexampleapp.core.di.modules

import com.example.daggerexampleapp.core.common.ImageLoader
import com.example.daggerexampleapp.core.common.ImageLoaderImpl
import dagger.Binds
import dagger.Module

/**
 * @author s.buvaka
 */
@Module
interface CommonModule {

    @Binds
    fun bindImageLoader(imageLoaderImpl: ImageLoaderImpl): ImageLoader
}
