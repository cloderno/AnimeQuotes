package com.example.animequotes.di

import com.example.animequotes.data.AnimeQuoteService
import com.example.animequotes.data.QuoteRepositoryImpl
import com.example.animequotes.domain.repository.QuoteRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideQuoteService(okHttpClient: OkHttpClient): AnimeQuoteService {
        val gson: Gson = GsonBuilder().create()

        return Retrofit.Builder()
            .baseUrl("https://animechan.xyz/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(AnimeQuoteService::class.java)
    }

    @Provides
    @Singleton
    fun provideQuoteRepository(characterService: AnimeQuoteService): QuoteRepository {
        return QuoteRepositoryImpl(characterService)
    }
}