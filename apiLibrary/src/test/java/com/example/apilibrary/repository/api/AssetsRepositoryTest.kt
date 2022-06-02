package com.example.apilibrary.repository.api

import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

internal class AssetsRepositoryTest {
    @Test
    fun testApi() {
        runTest {
            Assert.assertNotEquals(
                0, RetrofitRequestHelper.getListAssets().getAssets().size
            )
        }
    }
}
