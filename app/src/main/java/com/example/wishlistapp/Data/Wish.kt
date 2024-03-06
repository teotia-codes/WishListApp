package com.example.wishlistapp.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title: String = "",
    @ColumnInfo(name = "wish-desc")
    val description: String = ""
)
 object DummyWish{
     val wishList = listOf<Wish>(
         Wish(
             title = "iPhone 15 Pro Max",
             description = "Apple Product basically a mobile phone"
         ),
         Wish(
             title = "Vision Pro",
             description = "Apple Product basically a Vr headset"
         ),
         Wish(
             title =  "Macbook M2 pro",
             description = "Laptop "
         ),
     )
 }