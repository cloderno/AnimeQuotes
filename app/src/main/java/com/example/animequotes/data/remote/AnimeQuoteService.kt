package com.example.animequotes.data.remote

import retrofit2.http.GET

interface AnimeQuoteService {
//    https://animechan.xyz/api/random
    @GET("api/random")
    suspend fun animeQuote(): AnimeQuoteResponse
}