package com.example.cryptocurrencyapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.models.RetrofitRequestHelper
import com.example.apilibrary.repository.assets.Assets.AssetsItem
import com.example.cryptocurrencyapp.databinding.FavoriteFragmentBinding
import com.example.cryptocurrencyapp.view.adapters.CoinFavoriteAdapter
import com.example.cryptocurrencyapp.view.adapters.TinyDB
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel
import com.example.cryptocurrencyapp.viewmodel.restults.DataResult

class CoinFavoriteFragment : Fragment() {
    private lateinit var listAdapter: CoinFavoriteAdapter
    private lateinit var binding: FavoriteFragmentBinding
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private val coinViewModel: AssetsListViewModel by activityViewModels {
        RetrofitRequestHelper.getListAssets()
    }

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
        binding.favoritRecyclerView.layoutManager = staggeredGridLayoutManager
        binding.favoritRecyclerView.adapter = listAdapter
    }

    private fun collectAssetsObserver() {
        val dataBase = TinyDB(requireContext()).getAll()
        val favoritList = arrayListOf<AssetsItem?>()

        filterFavoriteAssets(dataBase, favoritList)
        listAdapter.submitList(favoritList)
    }

    private fun filterFavoriteAssets(
        dataBase: ArrayList<String>,
        favoritList: ArrayList<AssetsItem?>,
    ) {
        coinViewModel.assets.observe(viewLifecycleOwner) { assetsItem ->
            when (assetsItem) {
                is DataResult.Loading -> {
                    Log.d("", "Loading")
                }
                is DataResult.Sucess -> {
                    dataBase.forEach { databaseItem ->
                        val favoriteItem = assetsItem.data.find { assetsItem ->
                            assetsItem.asset_id == databaseItem
                        }
                        favoritList += favoriteItem
                    }
                }
                is DataResult.Error -> {
                    Log.d("", "erro")
                }
            }
        }
    }

    private fun goToCoinDetails(asset: AssetsItem) {
        val bundle = bundleOf("asset" to asset)
        findNavController().navigate(R.id.action_to_detailIcon, bundle)
    }
}
