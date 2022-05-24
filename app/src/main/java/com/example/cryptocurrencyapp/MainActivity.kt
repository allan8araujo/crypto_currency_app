package com.example.cryptocurrencyapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrencyapp.data.api.RetrofitInstance
import com.example.cryptocurrencyapp.databinding.ActivityMainBinding
import com.example.cryptocurrencyapp.viewmodel.AssetsListViewModel

class MainActivity : AppCompatActivity() {

    private val assetsListViewModel: AssetsListViewModel by viewModels {
        RetrofitInstance.assetsFactory
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}