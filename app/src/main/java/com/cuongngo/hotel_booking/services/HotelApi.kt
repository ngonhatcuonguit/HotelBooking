package com.cuongngo.hotel_booking.services

import com.cuongngo.hotel_booking.services.network.invoker.ApiClientFactory

interface HotelApi {


    companion object {
        operator fun invoke(): HotelApi {
            return ApiClientFactory.createService()
        }
    }
}