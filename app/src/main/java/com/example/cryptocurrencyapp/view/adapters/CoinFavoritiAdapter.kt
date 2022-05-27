package com.example.cryptocurrencyapp.view.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.data.api.retrofit.RetrofitRequestHelper
import com.example.cryptocurrencyapp.data.models.Assets.AssetsItem
import com.example.cryptocurrencyapp.data.models.Assets.funMockLives
import com.example.cryptocurrencyapp.databinding.FavoriteFragmentBinding
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel

class CoinFavoritiAdapter : Fragment() {
//
//        private lateinit var listAdapter: CoinFavoritAdapter
//    private lateinit var binding: FavoriteFragmentBinding
//    private lateinit var linearLayoutManager: LinearLayoutManager
//    private val coinViewModel: AssetsListViewModel by viewModels {
//        RetrofitRequestHelper.getListAssets()
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View {
//        binding = FavoriteFragmentBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setupRecycler()
//        collectAssetsObserver()
//}
//    private fun setupRecycler() {
//        listAdapter = CoinFavoritAdapter(requireContext()) { asset -> goToCoinDetails(asset as AssetsItem) }
//        linearLayoutManager = LinearLayoutManager(
//            activity,
//            LinearLayoutManager.VERTICAL,
//            false
//        )
////        binding.coinListRecyclerView.layoutManager = linearLayoutManager
////        binding.coinListRecyclerView.adapter = listAdapter
//
//
//    }
//
//
//        private fun collectAssetsObserver() {
////            coinViewModel.getAllAssets()
////            coinViewModel.assets.observe(viewLifecycleOwner) { assetsResults ->
////                setListAdapter(assetsResults)
////            }
//        setListAdapter(funMockLives())
//    }
//
//    private fun setListAdapter(list: List<AssetsItem>) {
//        listAdapter.submitList(list)
//
//    }
//
//    private fun goToCoinDetails(asset: AssetsItem) {
//        val bundle = bundleOf("asset" to asset)
//        findNavController().navigate(R.id.action_to_detailIcon, bundle)
//    }
}


