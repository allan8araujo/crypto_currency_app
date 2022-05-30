package com.example.cryptocurrencyapp.viewmodel

import com.example.cryptocurrencyapp.data.models.Assets.AssetsItem

sealed class DataResult<T> {
    class Loading<T> : DataResult<T>()
    class Sucess<T>(val data: T) : DataResult<T>()
    class Error<T>(val throwable: Throwable, val emptyDataResults: ArrayList<AssetsItem>) :
        DataResult<T>()
}