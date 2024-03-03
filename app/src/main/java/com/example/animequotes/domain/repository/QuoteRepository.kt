package com.example.animequotes.domain.repository

import com.example.animequotes.data.local.FavouriteQuote
import com.example.animequotes.domain.model.AnimeQuote

interface QuoteRepository {
    /**
     *  Boolean - Success
     *  String - data
     *
     * */
    suspend fun loadQuote(): Pair<Boolean, AnimeQuote>
    suspend fun saveQuoteToDatabase(quote: AnimeQuote)
    suspend fun getAllQuotesFromDb(): List<FavouriteQuote>
}