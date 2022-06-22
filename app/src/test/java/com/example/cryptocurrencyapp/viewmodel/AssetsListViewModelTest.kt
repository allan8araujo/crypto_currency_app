package com.example.cryptocurrencyapp.viewmodel

import com.example.models.AssetsDTO
import com.example.models.funMockAssets
import org.junit.Assert
import org.junit.Test

internal class AssetsListViewModelTest {

    //    @Test
//    fun givenServerResponseError_whenFetch_shouldReturnError() {
//        testCoroutineRule.runBlockingTest {
//            val someGeekyError = "Something is not right"
//            doThrow(RuntimeException(someGeekyError))
//                .`when`(gfgApi)
//                .getGfgUser()
//            val viewModel = SingleNetworkCallViewModel(gfgApi, gfgDBHelper)
//            viewModel.getGfgUser().observeForever(apiUsersObserver)
//            verify(gfgApi).getGfgUser()
//            verify(apiUsersObserver).onChanged(
//                Resource.error(
//                    RuntimeException(someGeekyError).toString(),
//                    null
//                )
//            )
//            viewModel.getGfgUser().removeObserver(apiUsersObserver)
//        }
//    }
    @Test
    fun `ao chamar a funcao toAssets com dados nao vazios, o resultado precisa ser diferente de nulo ou vazio`() {
        val assetDTO = com.example.models.AssetsDTO()
        assetDTO.addAll(
            com.example.models.funMockAssets()
        )
        val asset = assetDTO.toAssets()
        Assert.assertFalse(asset.isNullOrEmpty())
    }
}
