package com.example.animequotes.data

import androidx.room.RoomDatabase
import com.example.animequotes.data.local.FavouriteQuote
import com.example.animequotes.data.local.FavouriteQuoteDao

class FavouriteQuoteDaoImpl(database: AppDatabase) : FavouriteQuoteDao {
    private val dao = database.favouriteQuoteDao()

    override suspend fun insert(quote: FavouriteQuote) {
        dao.insert(quote)
    }

    override fun getQuote(quoteText: String): FavouriteQuote? {
        return dao.getQuote(quoteText)
    }

    override fun getAll(): List<FavouriteQuote> {
        return dao.getAll()
    }
}
