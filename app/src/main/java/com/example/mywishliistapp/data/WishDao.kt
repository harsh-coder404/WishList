package com.example.mywishliistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao                                   // TODO operations on DB use Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)                            // OnConflict - how to deal in case of a conflict happens (like same id)
    abstract suspend fun addWish(wishEntity: Wish)   // we use suspend as it will happen in parallel

    // way to write a query
    @Query("Select * from `wish-table`")       // Loads all the wishes from the wish table
        abstract fun getAllWishes(): Flow<List<Wish>>     // Flow return result of the query
    // we dont use suspend here as Flow already happens in coroutines

    @Update
    abstract suspend fun updateAWish(wishEntity: Wish)

    @Delete
    abstract suspend fun deleteAWish(wishEntity: Wish)

    @Query("Select * from `wish-table` where id=:id")                 // fetches a particular wish
    abstract fun getAWishById(id:Long): Flow<Wish>    //  Flow - happens asynchronously in coroutines
}