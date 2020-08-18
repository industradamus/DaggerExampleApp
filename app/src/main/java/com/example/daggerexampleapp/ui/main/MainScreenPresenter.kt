package com.example.daggerexampleapp.ui.main

/**
 * @author s.buvaka
 */
interface MainScreenPresenter {

    fun attach(view: MainScreenView)

    fun detach()

    fun loadPhotos()
}
