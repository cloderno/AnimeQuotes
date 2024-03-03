package com.example.animequotes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.animequotes.R
import com.example.animequotes.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.getNewQuoteButton.setOnClickListener {
            viewModel.load()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.loader.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.cardView.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
        }

        viewModel.animeQuote.observe(viewLifecycleOwner) { animeQuote ->
            binding.apply {
                if (animeQuote != null && animeQuote.quote != "Oops, too many quotes, try again later..") {
                    // Display the quote, character, and anime if animeQuote is not null and the quote is not the error message
                    quoteTxt.text = animeQuote.quote
                    characterTxt.text = "Character: ${animeQuote.character}"
                    animeTxt.text = "Anime: ${animeQuote.anime}"

                    saveQuoteButton.visibility = View.VISIBLE
                    characterTxt.visibility = View.VISIBLE
                    animeTxt.visibility = View.VISIBLE
                } else {
                    quoteTxt.text = animeQuote.quote

                    // Hide views if animeQuote is null or the quote is the error message
                    saveQuoteButton.visibility = View.GONE
                    characterTxt.visibility = View.GONE
                    animeTxt.visibility = View.GONE
                }
            }
        }

        binding.saveQuoteButton.setOnClickListener {
            viewModel.animeQuote.value?.let {
                viewModel.saveQuoteToDatabase(it)
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

}