package com.example.animequotes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// DAO interface
@Dao
interface FavouriteQuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quote: FavouriteQuote)

    @Query("SELECT * FROM favorite_quotes")
    fun getAll(): List<FavouriteQuote>
}
