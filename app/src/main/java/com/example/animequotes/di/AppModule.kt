package com.example.animequotes.di

import android.content.Context
import com.example.animequotes.App
import com.example.animequotes.data.AppDatabase
import com.example.animequotes.data.FavouriteQuoteDaoImpl
import com.example.animequotes.data.remote.AnimeQuoteService
import com.example.animequotes.data.QuoteRepositoryImpl
import com.example.animequotes.data.local.FavouriteQuoteDao
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
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        // You need to provide the context here. This can be the application context.
        return App.instance.applicationContext
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://animechan.xyz/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAnimeQuoteService(retrofit: Retrofit): AnimeQuoteService {
        return retrofit.create(AnimeQuoteService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideFavouriteQuoteDao(database: AppDatabase): FavouriteQuoteDao {
        return FavouriteQuoteDaoImpl(database)
    }

    @Provides
    @Singleton
    fun provideQuoteRepository(
        animeQuoteService: AnimeQuoteService,
        favouriteQuoteDao: FavouriteQuoteDao
    ): QuoteRepository {
        return QuoteRepositoryImpl(animeQuoteService, favouriteQuoteDao)
    }
}
