package com.example.abstraction


fun AssetsDTO.toAssets(): List<AssetsItem> {
    return this.map {
        it.toAssetsItem()
    }
}

fun AssetsItemDTO.toAssetsItem(): AssetsItem = AssetsItem(
    asset_id = this.asset_id,
    data_end = this.data_end,
    data_orderbook_end = this.data_orderbook_end,
    data_orderbook_start = this.data_orderbook_start,
    data_quote_end = this.data_quote_end,
    data_quote_start = this.data_quote_start,
    data_start = this.data_start,
    data_symbols_count = this.data_symbols_count,
    data_trade_end = this.data_trade_end,
    data_trade_start = this.data_trade_start,
    id_icon = this.id_icon,
    name = this.name,
    price_usd = this.price_usd,
    type_is_crypto = this.type_is_crypto,
    volume_1day_usd = this.volume_1day_usd,
    volume_1hrs_usd = this.volume_1hrs_usd,
    volume_1mth_usd = this.volume_1mth_usd,
)
