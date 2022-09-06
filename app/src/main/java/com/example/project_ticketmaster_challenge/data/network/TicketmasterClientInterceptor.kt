package com.example.project_ticketmaster_challenge.data.network

import okhttp3.Interceptor
import okhttp3.Response

class TicketmasterClientInterceptor(
    private val apiKey: String
): Interceptor {

    companion object {
        const val QUERY_PARAM_API_KEY = "apikey"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url()
        val newUrl = url.newBuilder()
            .addQueryParameter(
                QUERY_PARAM_API_KEY,
                apiKey,
            ).build()
        val newRequestBuilder = request.newBuilder().url(newUrl)
        val newRequest = newRequestBuilder.build()
        return chain.proceed(newRequest)
    }
}