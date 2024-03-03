package com.example.animequotes.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animequotes.data.FavouriteQuoteDaoImpl
import com.example.animequotes.data.local.FavouriteQuote
import com.example.animequotes.domain.model.AnimeQuote
import com.example.animequotes.domain.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: QuoteRepository
): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _animeQuote = MutableLiveData<AnimeQuote>()
    val animeQuote: LiveData<AnimeQuote> get() = _animeQuote

    fun load() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val quoteResponse = repository.loadQuote()
                val animeQuote = AnimeQuote(
                    anime = quoteResponse.second.anime,
                    character = quoteResponse.second.character,
                    quote = quoteResponse.second.quote
                )
                _animeQuote.value = animeQuote
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun saveQuoteToDatabase(quote: AnimeQuote) {
        viewModelScope.launch {
            repository.saveQuoteToDatabase(quote)
        }
    }

    fun getAllQuotes() {
        viewModelScope.launch(Dispatchers.IO) {
            val quotes = repository.getAllQuotesFromDb()
            quotes.forEach { quote ->
                Log.d("TAG", "Anime: ${quote.anime}, Character: ${quote.character}, Quote: ${quote.quote}")
            }
        }
    }

}