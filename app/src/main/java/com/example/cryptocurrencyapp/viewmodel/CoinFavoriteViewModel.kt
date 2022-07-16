package com.example.cryptocurrencyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.apilibrary.repository.const.Constants
import com.example.cryptocurrencyapp.databinding.FavoriteFragmentBinding
import com.example.cryptocurrencyapp.view.adapters.CoinFavoriteAdapter

class CoinFavoriteViewModel : ViewModel() {
    fun setupRecycler(
        listAdapter: CoinFavoriteAdapter,
        binding: FavoriteFragmentBinding,
    ) {
        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.currentDateTextView.text = Constants.DATE_NOW
        binding.favoritRecyclerView.layoutManager = staggeredGridLayoutManager
        binding.favoritRecyclerView.adapter = listAdapter
    }
}
