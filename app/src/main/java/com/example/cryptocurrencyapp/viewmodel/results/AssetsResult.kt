package com.example.cryptocurrencyapp.viewmodel.results

import com.example.abstraction.AssetsItem
import retrofit2.HttpException

sealed class DataResult<T> {
    class Loading<T> : DataResult<T>()
    class Success<T>(val data: T) : DataResult<T>()
    class Error<T>(val throwable: HttpException, val emptyDataResults: ArrayList<AssetsItem>) :
        DataResult<T>()
}
