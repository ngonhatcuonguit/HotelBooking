package com.cuongngo.hotel_booking.services.network.invoker

import com.cuongngo.hotel_booking.local.AppPreferences
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class BaseInterceptor : Interceptor {
    override fun intercept(chain: Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("Authorization", "Bearer ${AppPreferences.getUserAccessToken()}").build()
        return chain.proceed(request)
    }
}