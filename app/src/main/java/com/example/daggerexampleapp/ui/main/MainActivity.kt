package com.example.daggerexampleapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerexampleapp.R
import com.example.daggerexampleapp.core.common.ImageLoader
import com.example.daggerexampleapp.core.common.ImageLoaderImpl
import com.example.daggerexampleapp.core.di.components.DaggerAppComponent
import com.example.daggerexampleapp.core.models.Photo
import com.example.daggerexampleapp.core.network.PixelsApi
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainScreenView {

    private val imageLoader: ImageLoader = ImageLoaderImpl()
    private val imageAdapter by lazy { ImageAdapter(imageLoader) }
    private val presenter by lazy { MainScreenPresenter(pixelsApi) }

    @Inject
    lateinit var pixelsApi: PixelsApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.builder()
            .build()
            .inject(this)

        presenter.attach(this)

        initViews()
        presenter.loadPhotos()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.detach()
    }

    override fun updateUI(photos: List<Photo>) {
        imageAdapter.items = photos
    }

    private fun initViews() {
        imageRecycler.adapter = imageAdapter
    }
}
