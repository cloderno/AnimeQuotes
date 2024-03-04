package com.example.animequotes.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animequotes.R
import com.example.animequotes.databinding.FragmentFavouritesBinding
import com.example.animequotes.databinding.FragmentHomeBinding
import com.example.animequotes.presentation.adapter.FavouriteQuotesAdapter
import dagger.hilt.android.AndroidEntryPoint

import androidx.lifecycle.observe

@AndroidEntryPoint
class FavouritesFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentFavouritesBinding
    private val adapter = FavouriteQuotesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.getAllQuotes()

        viewModel.quotes.observe(viewLifecycleOwner) { quotes ->
            adapter.setFavoriteQuotes(quotes)
        }

        super.onViewCreated(view, savedInstanceState)
    }
}