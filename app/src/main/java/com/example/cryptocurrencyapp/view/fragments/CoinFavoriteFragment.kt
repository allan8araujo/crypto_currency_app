package com.example.cryptocurrencyapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.const.Constants.Companion.DATE_NOW
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.FavoriteFragmentBinding
import com.example.cryptocurrencyapp.view.adapters.CoinFavoriteAdapter
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel

class CoinFavoriteFragment : Fragment() {
    private lateinit var listAdapter: CoinFavoriteAdapter
    private lateinit var binding: FavoriteFragmentBinding
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private val coinViewModel: AssetsListViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FavoriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        collectAssetsObserver()
    }

    private fun setupRecycler() {
        listAdapter =
            CoinFavoriteAdapter(requireContext(), coinViewModel) { asset -> goToCoinDetails(asset) }
        staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.currentDateTextView.text = DATE_NOW
        binding.favoritRecyclerView.layoutManager = staggeredGridLayoutManager
        binding.favoritRecyclerView.adapter = listAdapter
    }

    private fun collectAssetsObserver() {
        coinViewModel.allFavoriteAssets.value
        coinViewModel.allFavoriteAssets.observe(viewLifecycleOwner) { assetsItem ->
            binding.favoriteScreenProgressBar.visibility = View.GONE
            listAdapter.submitList(assetsItem)
        }
    }

    private fun goToCoinDetails(asset: AssetsItem) {
        val bundle = bundleOf("asset" to asset)
        findNavController().navigate(R.id.action_to_detailIcon, bundle)
    }
}
