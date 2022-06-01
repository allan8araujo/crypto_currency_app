package com.example.cryptocurrencyapp.viewmodel.restults

import com.example.apilibrary.repository.assets.Assets.AssetsItem

sealed class DataResult<T> {
    class Loading<T> : DataResult<T>()
    class Sucess<T>(val data: T) : DataResult<T>()
    class Error<T>(val throwable: Throwable, val emptyDataResults: ArrayList<AssetsItem>) :
        DataResult<T>()
}
