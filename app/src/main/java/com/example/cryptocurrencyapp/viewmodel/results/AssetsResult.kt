package com.example.cryptocurrencyapp.viewmodel.results

import com.example.cryptocurrencyapp.models.assets.Assets.AssetsItem

sealed class DataResult<T> {
    class Loading<T> : DataResult<T>()
    class Sucess<T>(val data: T) : DataResult<T>()
    class Error<T>(val throwable: Throwable, val emptyDataResults: ArrayList<AssetsItem>) :
        DataResult<T>()
}
