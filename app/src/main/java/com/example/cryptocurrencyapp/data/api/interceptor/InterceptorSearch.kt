package com.example.cryptocurrencyapp.data.api.interceptor

import com.example.cryptocurrencyapp.data.const.Constants
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class InterceptorSearch(val searchId: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain
            .request()
            .url
            .newBuilder()
            .addEncodedPathSegment(searchId)
            .addQueryParameter("apikey", Constants.API_KEY)
            .build()

        val request: Request = chain
            .request()
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}
