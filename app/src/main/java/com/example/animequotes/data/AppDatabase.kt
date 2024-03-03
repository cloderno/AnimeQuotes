package com.example.animequotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.animequotes.data.local.FavouriteQuote
import com.example.animequotes.data.local.FavouriteQuoteDao

@Database(entities = [FavouriteQuote::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteQuoteDao(): FavouriteQuoteDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "favourite_quote_dao"
            ).build()
        }
    }
}
