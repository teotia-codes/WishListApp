package com.example.wishlistapp

import android.app.Application
import com.example.wishlistapp.Data.Graph


class WishListApp: Application() {
    //it is used to provide global context to the app.
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }

}