package com.example.cryptocurrencyapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityMainBinding

//    private val assetsListViewModel: AssetsListViewModel by viewModels {
//        RetrofitInstance.assetsFactory
//    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //
    private val controller by lazy {
        findNavController(R.id.activity_main_navHost)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mainBottomNavigation.setupWithNavController(controller)
    }
}
