package com.example.apilibrary.repository.api

import com.example.apilibrary.repository.api.retrofit.RetrofitRequestHelper
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

internal class AssetsRequestTest {
    @Test
    fun testApi() {
        runTest {
            Assert.assertNotEquals(
                0, RetrofitRequestHelper.getListAssets().getAssets().size
            )
        }
    }
}
