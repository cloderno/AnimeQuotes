package com.example.animequotes.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AnimeQuoteResponse (
    @Expose
    @SerializedName("anime")
    val anime : String,
    @Expose
    @SerializedName("character")
    val character: String,
    @Expose
    @SerializedName("quote")
    val quote: String
)