package com.example.cryptocurrencyapp.data.models.Assets


data class AssetsItem(
    val asset_id: String,
    val data_end: String? = null,
    val data_orderbook_end: String? = null,
    val data_orderbook_start: String? = null,
    val data_quote_end: String? = null,
    val data_quote_start: String? = null,
    val data_start: String? = null,
    val data_symbols_count: Int,
    val data_trade_end: String? = null,
    val data_trade_start: String? = null,
    val id_icon: String? = null,
    val name: String,
    val price_usd: Double? = null,
    val type_is_crypto: Int,
    val volume_1day_usd: Double,
    val volume_1hrs_usd: Double,
    val volume_1mth_usd: Double,
)
