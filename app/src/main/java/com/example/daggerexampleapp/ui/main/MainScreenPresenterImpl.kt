package com.example.daggerexampleapp.ui.main

import android.util.Log
import com.example.daggerexampleapp.core.network.PixelsApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author s.buvaka
 */
class MainScreenPresenterImpl @Inject constructor(private val api: PixelsApi) :
    MainScreenPresenter {

    private val compositeDisposable = CompositeDisposable()
    private var view: MainScreenView? = null

    override fun attach(view: MainScreenView) {
        this.view = view
    }

    override fun detach() {
        compositeDisposable.clear()
        view = null
    }

    override fun loadPhotos() {
        compositeDisposable.add(
            api.getImageList(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response -> response.body()?.photos?.let { view?.updateUI(it) } },
                    { error -> Log.e("MainScreenPresenter", error.message ?: "Unknown Exception") }
                )
        )
    }
}
