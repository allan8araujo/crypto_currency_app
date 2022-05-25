package com.example.cryptocurrencyapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrencyapp.data.api.RetrofitInstance
import com.example.cryptocurrencyapp.databinding.ActivityMainBinding
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel
import com.example.cryptocurrencyapp.viewmodel.SearchedAssetsViewModel

class MainActivity : AppCompatActivity() {

    private val assetsListViewModel: AssetsListViewModel by viewModels {
        RetrofitInstance.getAllAssets()
    }

    private val searchAssetViewModel: SearchedAssetsViewModel by viewModels {
        RetrofitInstance.searchAssets("USD")
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    /**    assetsListViewModel.getAllAssets()
     searchAssetViewModel.searchedAsset()
     Log.d("tag", assetsListViewModel.assets.value .toString())
     Log.d("tag", searchAssetViewModel.asset.value .toString())
     **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
