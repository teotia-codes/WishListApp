package com.example.wishlistapp.Data

import android.content.Context
import androidx.room.Room

object Graph {
    //it is only one and not created multiple times.
    //does the main work as provide prop such as wishrepository and database.

    lateinit var database: WishDatabase
    val wishrepository by lazy {
        //by lazy ensures that the variable is intialised when it is required and not as soon as app starts.
        Wishrepository(wishDao = database.wishDao())
    }
    fun provide(context: Context){
        database = Room.databaseBuilder(context,
            name = "wishlist.db" ,
            klass = WishDatabase::class.java ).build()
    }
}