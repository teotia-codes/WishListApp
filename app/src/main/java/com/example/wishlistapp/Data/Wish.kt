package com.example.wishlistapp.Data

data class Wish(
    val id: Long = 0L,
    val title: String = "",
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