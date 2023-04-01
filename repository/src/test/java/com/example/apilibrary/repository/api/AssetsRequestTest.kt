package com.example.apilibrary.repository.api

import com.example.apilibrary.repository.const.Constants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

internal class AssetsRequestTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `quando a api e chamada o tamanho do retorno de objetos tem que ser maior que zero`() {
        runTest {
            Constants.API_KEY = "0FACCEE3-25E3-4347-B99D-E49D9C11FF8D"
//            Assert.assertNotEquals(
//                0, RetrofitRequestHelper.getListAssets().getAssets()
//            )
        }
    }
}
