package com.example.cryptocurrencyapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencyapp.data.api.RetrofitInstance
import com.example.cryptocurrencyapp.data.models.Assets
import com.example.cryptocurrencyapp.data.models.AssetsItem
import com.example.cryptocurrencyapp.databinding.CoinListFragmentBinding
import com.example.cryptocurrencyapp.view.adapters.CoinListAdapter
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel

class CoinListFragment : Fragment() {

    private lateinit var listAdapter: CoinListAdapter
    private lateinit var binding: CoinListFragmentBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val coinViewModel: AssetsListViewModel by viewModels {
        RetrofitInstance.assetsFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CoinListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        collectAssetsObserver()
    }

    private fun setupRecycler() {
        listAdapter = CoinListAdapter() { asset -> goToCoinDetails() }
        linearLayoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.coinListRecyclerView.layoutManager = linearLayoutManager
        binding.coinListRecyclerView.adapter = listAdapter
    }

    private fun collectAssetsObserver(){
        coinViewModel.getAllAssets()
        coinViewModel.assets.observe(viewLifecycleOwner){
            assetsResults -> setListAdapter(assetsResults)

        }
    }

    private fun setListAdapter(list : List<AssetsItem>){
        listAdapter.submitList(list)
    }

    private fun goToCoinDetails() {
        Toast.makeText(context, "to details", Toast.LENGTH_LONG).show()
    }

}