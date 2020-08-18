package com.example.daggerexampleapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerexampleapp.R
import com.example.daggerexampleapp.core.common.ImageLoader
import com.example.daggerexampleapp.core.di.components.DaggerAppComponent
import com.example.daggerexampleapp.core.models.Photo
import kotlinx.android.synthetic.main.list_item_image.view.*
import javax.inject.Inject

/**
 * @author s.buvaka
 */
class ImageAdapter(private val imageLoader: ImageLoader) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    var items: List<Photo> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_image, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], imageLoader)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal fun bind(photo: Photo, imageLoader: ImageLoader) {
            with(itemView) {
                imageLoader.loadImage(photo.src.medium, imageContainer)
            }
        }
    }
}
