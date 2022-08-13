package com.cuongngo.hotel_booking.services.remote

import com.cuongngo.hotel_booking.services.HotelApi
import com.cuongngo.hotel_booking.services.network.BaseRemoteDataSource

class HotelRemoteDataSource(private val apiService: HotelApi) : BaseRemoteDataSource() {


    suspend fun getListHotel(
        filtering: String? = null,
        city_id: String? = null,
        count: String? = null,
        after: String? = null,
        name: String? = null
    ) = getResult {
        apiService.getListHotel(filtering, city_id, count, after, name)
    }
}