package com.example.wishlistapp.Data

import com.example.wishlistapp.WishDao
import kotlinx.coroutines.flow.Flow

class Wishrepository(private val wishDao: WishDao) {
    suspend fun addWish(wish: Wish){
        wishDao.addWish(wish)
    }

    fun getWish(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun getAWishById(id:Long) : Flow<Wish> {
        return wishDao.getAWishById(id)
    }
    suspend fun updateAWish(wish: Wish) {
        wishDao.updateAWish(wish)
    }
    suspend fun deleteAWish(wish: Wish){
        wishDao.deleteWish(wish)
    }
}