package com.example.daggerexampleapp.core.common

import android.widget.ImageView
import com.bumptech.glide.Glide
import javax.inject.Inject

/**
 * @author s.buvaka
 */
interface ImageLoader {

    fun loadImage(url: String, into: ImageView)
}

class ImageLoaderImpl @Inject constructor() : ImageLoader {

    override fun loadImage(url: String, into: ImageView) {
        Glide.with(into)
            .load(url)
            .into(into)
    }
}
