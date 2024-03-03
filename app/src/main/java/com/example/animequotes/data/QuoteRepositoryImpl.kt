package com.example.animequotes.data

import com.example.animequotes.data.local.FavouriteQuote
import com.example.animequotes.data.local.FavouriteQuoteDao
import com.example.animequotes.data.remote.AnimeQuoteService
import com.example.animequotes.domain.model.AnimeQuote
import com.example.animequotes.domain.repository.QuoteRepository
import retrofit2.HttpException

class QuoteRepositoryImpl(
    private val service: AnimeQuoteService,
    private val dao: FavouriteQuoteDao
): QuoteRepository {
    override suspend fun loadQuote(): Pair<Boolean, AnimeQuote> {
        return try {
            val animeQuoteResponse = service.animeQuote()
            Pair(true, AnimeQuote(
                anime = animeQuoteResponse.anime,
                character = animeQuoteResponse.character,
                quote = animeQuoteResponse.quote
            ))
        } catch (e: HttpException) {
            // Handle HTTP errors (e.g., 404, 429, etc.)
            Pair(false, AnimeQuote("", "", "Oops, too many quotes, try again later.."))
        } catch (e: Exception) {
            // Handle other exceptions
            Pair(false, AnimeQuote("", "", "Error: ${e.message ?: "Unknown error"}"))
        }
    }

    override suspend fun saveQuoteToDatabase(quote: AnimeQuote) {
        val favouriteQuote = FavouriteQuote(
            anime = quote.anime,
            character = quote.character,
            quote = quote.quote
        )
        dao.insert(favouriteQuote)
    }

    override suspend fun getAllQuotesFromDb(): List<FavouriteQuote> {
        return dao.getAll()
    }

}