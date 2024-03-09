package com.example.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.Data.Graph
import com.example.wishlistapp.Data.Wish
import com.example.wishlistapp.Data.Wishrepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishrepository: Wishrepository = Graph.wishrepository
): ViewModel() {
     var wishTitleState by mutableStateOf("")
    var wishDescription by mutableStateOf("")

    fun onWishTitleChanged(newString : String){
        wishTitleState = newString
    }

    fun onWishDesChanged(newString : String){
        wishDescription = newString
    }
    //Everytime with flow you have to use lateinit .
    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishrepository.getWish()
        }
    }

    fun addWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishrepository.addWish(wish)
        }
    }
    fun getAWishById(id:Long):Flow<Wish> {
        return wishrepository.getAWishById(id)
    }
        fun updateWish(wish: Wish){
            viewModelScope.launch(Dispatchers.IO) {
                wishrepository.updateAWish(wish)
            }
    }
    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishrepository.deleteAWish(wish)
        }
    }
}