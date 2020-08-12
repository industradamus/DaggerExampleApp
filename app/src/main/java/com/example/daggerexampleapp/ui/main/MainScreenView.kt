package com.example.daggerexampleapp.ui.main

import com.example.daggerexampleapp.core.models.Photo

/**
 * @author s.buvaka
 */
interface MainScreenView {

    fun updateUI(photos: List<Photo>)
}
