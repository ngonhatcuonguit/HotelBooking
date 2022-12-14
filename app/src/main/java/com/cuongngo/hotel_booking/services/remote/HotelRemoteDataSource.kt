package com.cuongngo.hotel_booking.services.remote

import com.cuongngo.hotel_booking.services.HotelApi
import com.cuongngo.hotel_booking.services.network.BaseRemoteDataSource
import retrofit2.http.Query

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

    suspend fun cancelBooking(
        booking_id: Int
    ) = getResult {
        apiService.cancelBooking(booking_id)
    }

    suspend fun getBookingDetail(
        booking_id: Int
    ) = getResult {
        apiService.getBookingDetail(booking_id)
    }


    suspend fun getListMyBooking(
        after: String? = null
    ) = getResult {
        apiService.getListMyBooking(after)
    }

}