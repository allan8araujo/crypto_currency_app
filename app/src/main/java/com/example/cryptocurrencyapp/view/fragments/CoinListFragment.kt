package com.example.cryptocurrencyapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.abstraction.AssetsItem
import com.example.apilibrary.repository.const.Constants.Companion.DATE_NOW
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.CoinListFragmentBinding
import com.example.cryptocurrencyapp.view.adapters.CoinListAdapter
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import com.example.cryptocurrencyapp.viewmodel.states.DataResult

class CoinListFragment : Fragment() {

    private lateinit var listAdapter: CoinListAdapter
    private lateinit var binding: CoinListFragmentBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val coinViewModel: CoinListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = CoinListFragmentBinding.inflate(inflater, container, false)
        binding.mainScreenProgressBar.visibility = View.GONE
        setupRecycler()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectAssetsObserver()
    }

    private fun setupRecycler() {
        listAdapter =
            CoinListAdapter(viewLifecycleOwner, coinViewModel) { asset -> goToCoinDetails(asset) }

        settingRecyclerViewProperties()
        binding.currentDateTextView.text = DATE_NOW
        binding.imgMenu.setOnClickListener { onClick ->
            settingUpMenu(onClick)
        }
        binding.searchEditText.setOnQueryTextListener(
            onQueryTextListener()
        )
    }

    private fun onQueryTextListener() = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(name: String?): Boolean {
            searchFilter(name)
            return false
        }

        override fun onQueryTextChange(name: String?): Boolean {
            searchFilter(name)
            return false
        }
    }

    private fun settingUpMenu(it: View?) {
        val popupmenu = PopupMenu(context, it)
        popupmenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.list_crypto_menu -> {
                    filterType(1)
                    true
                }
                R.id.list_currency_menu -> {
                    filterType(0)
                    true
                }
                R.id.list_all_menu -> {
                    observeAndSetList()
                    true
                }
                else -> false
            }
        }
        popupmenu.inflate(R.menu.menu_suspenso)
        popupmenu.show()
    }

    private fun filterType(cryptoType: Any?) {
        coinViewModel.assetsFromResultApi.observe(viewLifecycleOwner) { dataResults ->
            val newlist = coinViewModel.filterType_(dataResults, cryptoType)
            setListAdapter(newlist)
        }
    }

    private fun searchFilter(searchValue: String?) {
        coinViewModel.assetsFromResultApi.observe(viewLifecycleOwner) { dataResults ->
            if (searchValue != "") {
                coinViewModel.searchInList(searchValue, dataResults, listAdapter)
            } else {
                observeAndSetList()
            }
        }
    }

    private fun settingRecyclerViewProperties() {
        linearLayoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.coinListRecyclerView.layoutManager = linearLayoutManager
        binding.coinListRecyclerView.adapter = listAdapter
    }

    private fun collectAssetsObserver() {
        observeAndSetList()
    }

    private fun observeAndSetList() {
        coinViewModel.assetsFromResultApi.observe(viewLifecycleOwner) { dataResults ->
            when (dataResults) {
                is DataResult.Loading -> {
                    binding.mainScreenProgressBar.visibility = View.VISIBLE
                }
                is DataResult.Success -> {
                    binding.mainScreenProgressBar.visibility = View.GONE
                    setListAdapter(dataResults.data)
                }
                else -> {
                    binding.mainScreenProgressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_coinList_to_errorScreen)
                }
            }
        }
    }

    private fun setListAdapter(list: List<AssetsItem>?) {
        listAdapter.submitList(list) {
            binding.coinListRecyclerView.smoothScrollToPosition(0)
        }
    }

    private fun goToCoinDetails(asset: AssetsItem) {
        val bundle = bundleOf("asset" to asset)
        findNavController().navigate(R.id.action_to_detailIcon, bundle)
    }
}
