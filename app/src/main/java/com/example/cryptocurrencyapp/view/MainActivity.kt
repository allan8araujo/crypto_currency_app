package com.example.cryptocurrencyapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.R.id.*
import com.example.cryptocurrencyapp.data.api.retrofit.RetrofitRequestHelper
import com.example.cryptocurrencyapp.databinding.ActivityMainBinding
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val controller by lazy {
        findNavController(R.id.activity_main_navHost)
    }

    private val coinViewModel: AssetsListViewModel by viewModels {
        RetrofitRequestHelper.getListAssets()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBottomNav()
        binding.mainBottomNavigation.setupWithNavController(controller)
    }

    private fun setupBottomNav() {
        controller.addOnDestinationChangedListener {_,destination,_ ->
            when(destination.id){
                coinList, coinFavorites, coinDetails -> {
                    visibilityButtonNavigation()
                }
                else -> hideButtonNavigation()
            }
        }
    }

    private fun visibilityButtonNavigation() {
        binding.mainBottomNavigation.visibility = View.VISIBLE
    }
    private fun hideButtonNavigation(){
        binding.mainBottomNavigation.visibility = View.GONE
    }
}


