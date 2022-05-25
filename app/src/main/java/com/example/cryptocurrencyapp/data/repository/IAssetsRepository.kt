package com.example.cryptocurrencyapp.data.repository

import com.example.cryptocurrencyapp.data.models.Assets.Assets

interface IAssetsRepository {
    suspend fun getAssets(): Assets
    suspend fun searchAsset(): Assets
}
