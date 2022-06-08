package com.example.cryptocurrencyapp.viewmodel

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

internal class AssetsListViewModelTest {
    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val someGeekyError = "Something is not right"
            doThrow(RuntimeException(someGeekyError))
                .`when`(gfgApi)
                .getGfgUser()
            val viewModel = SingleNetworkCallViewModel(gfgApi, gfgDBHelper)
            viewModel.getGfgUser().observeForever(apiUsersObserver)
            verify(gfgApi).getGfgUser()
            verify(apiUsersObserver).onChanged(
                Resource.error(
                    RuntimeException(someGeekyError).toString(),
                    null
                )
            )
            viewModel.getGfgUser().removeObserver(apiUsersObserver)
        }
    }
}