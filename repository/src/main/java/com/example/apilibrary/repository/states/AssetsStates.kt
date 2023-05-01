package com.example.apilibrary.repository.states

import com.example.abstraction.AssetsItemDTO

sealed class DataResult<out T> {
    class Success<out T>(val data: T) : DataResult<T>()
    class Error<T>(val throwable: Throwable, val emptyDataResults: List<AssetsItemDTO>) :
        DataResult<T>()

    object Loading : DataResult<Nothing>()
}
