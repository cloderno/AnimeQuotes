package com.example.animequotes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animequotes.data.local.FavouriteQuote
import com.example.animequotes.databinding.ItemFavoriteQuoteBinding

class FavouriteQuotesAdapter : RecyclerView.Adapter<FavouriteQuotesAdapter.QuoteViewHolder>() {
    private var favoriteQuotes: List<FavouriteQuote> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding = ItemFavoriteQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = favoriteQuotes[position]
        holder.bind(quote)
    }

    override fun getItemCount(): Int {
        return favoriteQuotes.size
    }

    fun setFavoriteQuotes(quotes: List<FavouriteQuote>) {
        favoriteQuotes = quotes
        notifyDataSetChanged()
    }

    inner class QuoteViewHolder(private val binding: ItemFavoriteQuoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quote: FavouriteQuote) {
            binding.apply {
                animeTxt.text = quote.anime
                characterTxt.text = quote.character
                quoteTxt.text = quote.quote
            }
        }
    }
}
