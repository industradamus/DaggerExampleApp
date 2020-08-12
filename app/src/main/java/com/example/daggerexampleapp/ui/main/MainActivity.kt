package com.example.daggerexampleapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.daggerexampleapp.R
import com.example.daggerexampleapp.core.common.ImageLoader
import com.example.daggerexampleapp.core.common.ImageLoaderImpl
import com.example.daggerexampleapp.core.models.Photo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainScreenView {

    private val imageLoader: ImageLoader = ImageLoaderImpl()
    private val imageAdapter = ImageAdapter(imageLoader)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    override fun updateUI(photos: List<Photo>) {
        imageAdapter.items = photos
    }

    private fun initViews() {
        imageRecycler.adapter = imageAdapter
    }
}
