package com.example.cryptocurrencyapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.data.api.retrofit.RetrofitRequestHelper
import com.example.cryptocurrencyapp.data.models.Assets.AssetsItem
import com.example.cryptocurrencyapp.databinding.CoinListFragmentBinding
import com.example.cryptocurrencyapp.view.adapters.CoinListAdapter
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel

class CoinListFragment : Fragment() {

    private lateinit var listAdapter: CoinListAdapter
    private lateinit var binding: CoinListFragmentBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val coinViewModel: AssetsListViewModel by viewModels {
        RetrofitRequestHelper.getListAssets()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
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
        coinViewModel.getAllAssets()
        listAdapter = CoinListAdapter(coinViewModel) { asset -> goToCoinDetails() }
        settingRecyclerViewProperties()
        binding.imgMenu.setOnClickListener { onClick ->
            settingUpMenu(onClick)
        }
        binding.searchEditText.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(name: String?): Boolean {
                    searchFilter(name)
                    return false
                }

                override fun onQueryTextChange(name: String?): Boolean {
                    searchFilter(name)
                    return false
                }
            }
        )
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
                    setListAdapter(coinViewModel.assets.value)
                    true
                }
                else -> false
            }
        }
        popupmenu.inflate(R.menu.menu_suspenso)
        popupmenu.show()
    }

    private fun filterType(cryptoType: Any?) {
        val newlist = coinViewModel.assets.value?.filter {

            it.type_is_crypto == cryptoType
        }
        setListAdapter(newlist)
    }

    private fun searchFilter(searchValue: String?) {
        val listResults: List<AssetsItem>?
        if (searchValue != "") {
            val searchValueUpperCase = searchValue?.uppercase()
            val listResults = coinViewModel.assets.value?.filter {
                (it.asset_id.uppercase() in searchValueUpperCase!!) ||
                    (it.name.uppercase() in searchValueUpperCase!!)
            }
            setListAdapter(listResults)
        } else {
            listResults = coinViewModel.assets.value
            setListAdapter(listResults)
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
        coinViewModel.assets.observe(viewLifecycleOwner) { assetsResults ->
            setListAdapter(assetsResults)
        }
    }

    private fun setListAdapter(list: List<AssetsItem>?) {
        listAdapter.submitList(list)
    }

    private fun goToCoinDetails() {
        Toast.makeText(context, "to details", Toast.LENGTH_LONG).show()
    }
}
