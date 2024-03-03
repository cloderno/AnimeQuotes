package com.example.animequotes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity class
@Entity(tableName = "favorite_quotes")
data class FavouriteQuote(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val anime: String,
    val character: String,
    val quote: String
)
