package com.example.animequotes.data

import retrofit2.Call
import retrofit2.http.GET

interface AnimeQuoteService {
//    https://animechan.xyz/api/random
    @GET("api/random")
    suspend fun animeQuote(): AnimeQuoteResponse
}