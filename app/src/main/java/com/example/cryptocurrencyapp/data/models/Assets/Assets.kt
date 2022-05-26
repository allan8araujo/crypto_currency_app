package com.example.cryptocurrencyapp.data.models.Assets

class Assets : ArrayList<AssetsItem>()

val listMockedAssetsItems = arrayListOf(
    AssetsItem(
        asset_id = "BTC",
        name = "Bitcoin",
        type_is_crypto = 1,
        data_quote_start = "2014-02-24T17:43:05.0000000Z",
        data_quote_end = "2022-05-20T20:35:39.7726302Z",
        data_orderbook_start = "2014-02-24T17:43:05.0000000Z",
        data_orderbook_end = "2020-08-05T14:38:38.3413202Z",
        data_trade_start = "2010-07-17T23:09:17.0000000Z",
        data_trade_end = "2022-05-20T20:30:56.1480000Z",
        data_symbols_count = 93805,
        volume_1hrs_usd = 2751036552999.25,
        volume_1day_usd = 72323250346384.91,
        volume_1mth_usd = 4686460436739055898.64,
        price_usd = 29175.437763873381605904279504,
        id_icon = "4caf2b16-a017-4e26-a348-2cea69c34cba",
        data_start = "2010-07-17",
        data_end = "2022-05-20"
    ),
    AssetsItem(
        asset_id = "USD",
        name = "US Dollar",
        type_is_crypto = 0,
        data_quote_start = "2014-02-24T17:43:05.0000000Z",
        data_quote_end = "2022-05-20T20:35:39.5643474Z",
        data_orderbook_start = "2014-02-24T17:43:05.0000000Z",
        data_orderbook_end = "2020-08-05T14:38:00.7082850Z",
        data_trade_start = "2010-07-17T23:09:17.0000000Z",
        data_trade_end = "2022-05-20T20:30:56.3968230Z",
        data_symbols_count = 110646,
        volume_1hrs_usd = 609939509196.15,
        volume_1day_usd = 14528654658029.37,
        volume_1mth_usd = 555174170860704.13,
        id_icon = "0a4185f2-1a03-4a7c-b866-ba7076d8c73b",
        data_start = "2010-07-17",
        data_end = "2022-05-20"
    ),
    AssetsItem(
        asset_id = "PLN",
        name = "Zloty",
        type_is_crypto = 0,
        data_quote_start = "2017-08-29T15:47:10.0528025Z",
        data_quote_end = "2022-05-20T20:35:22.3530000Z",
        data_orderbook_start = "2017-08-29T15:47:10.0528025Z",
        data_orderbook_end = "2020-08-05T14:37:41.6530000Z",
        data_trade_start = "2011-04-05T18:49:48.0000000Z",
        data_trade_end = "2022-05-20T20:30:55.9000000Z",
        data_symbols_count = 95,
        volume_1hrs_usd = 202316.19,
        volume_1day_usd = 3603164.81,
        volume_1mth_usd = 140060433.83,
        price_usd = 0.2259039246019847445590339224,
        id_icon = "3f682b5b-77ec-4d8c-b612-b8ff3ac748f7",
        data_start = "2011-04-05",
        data_end = "2022-05-20"
    ),
    AssetsItem(
        asset_id = "EUR",
        name = "Euro",
        type_is_crypto = 0,
        data_quote_start = "2014-04-20T15:06:33.0000000Z",
        data_quote_end = "2022-05-20T20:35:37.9285496Z",
        data_orderbook_start = "2014-04-20T15:06:33.0000000Z",
        data_orderbook_end = "2020-08-05T14:38:06.5604509Z",
        data_trade_start = "2011-04-06T02:17:41.0000000Z",
        data_trade_end = "2022-05-20T20:30:51.3580000Z",
        data_symbols_count = 2336,
        volume_1hrs_usd = 26458833010.58,
        volume_1day_usd = 162645696327.49,
        volume_1mth_usd = 5383794299033.90,
        price_usd = 1.0553352291207959951023727716,
        id_icon = "688fcf1c-92bb-4c84-ac95-0971e9bfed2f",
        data_start = "2011-04-06",
        data_end = "2022-05-20"
    ),
    AssetsItem(
        asset_id = "USD",
        name = "US Dollar",
        type_is_crypto = 0,
        data_symbols_count = 110646,
        volume_1hrs_usd = 609939509196.15,
        volume_1day_usd = 14528654658029.37,
        volume_1mth_usd = 555174170860704.13,
    )
)

fun funMockLives() = listMockedAssetsItems
