package com.example.animequotes.data

import com.example.animequotes.domain.repository.QuoteRepository

class QuoteRepositoryImpl(
    private val service: AnimeQuoteService
): QuoteRepository {
    override suspend fun loadQuote(): Pair<Boolean, String> {
        return try {
            val quote = service.animeQuote().quote
            Pair(true, quote)
        } catch (e: Exception) {
            Pair(false, e.message ?: "Error")
        }
    }
}