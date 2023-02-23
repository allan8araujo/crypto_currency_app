package com.example.cryptocurrencyapp.view

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.apilibrary.repository.Repository
import com.example.apilibrary.repository.const.Constants
import com.example.apilibrary.repository.database.AssetsDatabase
import com.example.cryptocurrencyapp.R.id.*
import com.example.cryptocurrencyapp.databinding.ActivityMainBinding
import com.example.cryptocurrencyapp.ui.NavigationMain
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import com.example.cryptocurrencyapp.viewmodel.factories.ListViewModelFactory

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val controller by lazy {
        findNavController(activity_main_navHost)
    }

    private val database by lazy { AssetsDatabase.getDatabase(this) }

    private val coinViewModel: CoinListViewModel by viewModels {
        ListViewModelFactory(Repository(database.assetsDao()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//        setupApplication()

        setContent() {
            Main()
        }
    }

    private fun setupApplication() {
        getSecretKey()
        coinViewModel.getAllAssets()
        setupBottomNav()
    }

    private fun getSecretKey() {
        val applicationInfo: ApplicationInfo = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val value = applicationInfo.metaData["apiKey"]
        Constants.API_KEY = value.toString()
    }

    private fun setupBottomNav() {
        controller.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                coinList, coinFavorites, coinDetails, errorScreen -> {
                    visibilityButtonNavigation()
                }
                else -> hideButtonNavigation()
            }
        }
        binding.mainBottomNavigation.setupWithNavController(controller)
    }

    override fun onBackPressed() {
        when (controller.currentDestination!!.id) {
            coinDetails -> super.onBackPressed()
        }
    }

    private fun visibilityButtonNavigation() {
        binding.mainBottomNavigation.isVisible = true
    }

    private fun hideButtonNavigation() {
        binding.mainBottomNavigation.isVisible = false
    }
}
