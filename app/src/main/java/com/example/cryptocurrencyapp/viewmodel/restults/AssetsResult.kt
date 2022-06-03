package com.example.cryptocurrencyapp.viewmodel

import com.example.cryptocurrencyapp.models.assets.Assets.AssetsItem
import retrofit2.HttpException

sealed class DataResult<T> {
    class Loading<T> : DataResult<T>()
    class Sucess<T>(val data: T) : DataResult<T>()
    class Error<T>(val throwable: HttpException, val emptyDataResults: ArrayList<AssetsItem>) :
        DataResult<T>()
}
