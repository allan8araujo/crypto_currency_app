package com.example.abstraction

import java.io.Serializable

data class AssetCecko(
    val description: Description,
    val id: String,
    val name: String,
    val symbol: String,
    val market_data: MarketData
) : Serializable

data class MarketData(
    val current_price: CurrentPrice
) : Serializable

data class CurrentPrice(
    val eur: Double,
    val usd: Double,
    val btc: Double,
    val brl: Double,
    val eth: Double,
    val jpy: Double,
) : Serializable

data class Description(
    val en: String
) : Serializable