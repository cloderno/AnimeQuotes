package com.example.animequotes.domain.repository

interface QuoteRepository {
    /**
     *  Boolean - Success
     *  String - data
     *
     * */
    suspend fun loadQuote(): Pair<Boolean, String>
}