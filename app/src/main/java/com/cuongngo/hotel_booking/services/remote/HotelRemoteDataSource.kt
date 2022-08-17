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

    suspend fun getDetailHotel(
        hotel_id: Int
    ) = getResult {
        apiService.getDetailHotel(hotel_id)
    }

    suspend fun booking(
        guest: Int,
        check_in: String,
        check_out: String,
        hotel_id: Int
    ) = getResult {
        apiService.booking(guest, check_in, check_out, hotel_id)
    }

    suspend fun getListMyBooking() = getResult {
        apiService.getListMyBooking()
    }

}