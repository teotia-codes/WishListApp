package com.example.wishlistapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.wishlistapp.Data.Wish
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {
    //class with methods which don't have any body
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addWish(wishEntity: Wish)

    @Query("Select * from `wish-table`")
    abstract  fun getAllWishes(): Flow<List<Wish>>

    @Update
    abstract suspend fun updateAWish(wishEntity: Wish)

    @Delete
    abstract  suspend fun deleteWish(wishEntity: Wish)

    @Query("Select * from `wish-table` where id =:id")
    abstract   fun getAWishById(id: Long): Flow<List<Wish>>

}