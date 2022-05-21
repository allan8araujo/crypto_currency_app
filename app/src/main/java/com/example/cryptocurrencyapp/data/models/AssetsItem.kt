package com.example.cryptocurrencyapp.data.models

data class AssetsItem(
    val asset_id: String, //need
    val name: String, //need
    val type_is_crypto: Int, //need
    val volume_1day_usd: Double, // need
    val volume_1hrs_usd: Double, //need
    val volume_1mth_usd: Double, //need
    val data_symbols_count: Int, //need
    val data_end: String?=null,
    val data_orderbook_end: String?=null,
    val data_orderbook_start: String?=null,
    val data_quote_end: String?=null,
    val data_quote_start: String?=null,
    val data_start: String?=null,
    val data_trade_end: String?=null,
    val data_trade_start: String?=null,
    val id_icon: String?=null,
    val price_usd: Double?=null,
)

