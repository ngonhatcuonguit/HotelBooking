package com.cuongngo.hotel_booking.services.repository

import com.cuongngo.hotel_booking.response.*
import com.cuongngo.hotel_booking.services.network.BaseResult
import com.cuongngo.hotel_booking.services.remote.HotelRemoteDataSource
import retrofit2.http.Query

class HotelRepository(private val hotelRemoteDataSource: HotelRemoteDataSource) {

    suspend fun getListHotel(
        filtering: String? = null,
        city_id: String? = null,
        count: String? = null,
        after: String? = null,
        name: String? = null
    ): BaseResult<HotelResponse> {
        return hotelRemoteDataSource.getListHotel(filtering, city_id, count, after, name)
    }

    suspend fun getDetailHotel(
        hotel_id: Int
    ): BaseResult<HotelDetailResponse> {
        return hotelRemoteDataSource.getDetailHotel(hotel_id)
    }

    suspend fun booking(
        guest: Int,
        check_in: String,
        check_out: String,
        hotel_id: Int
    ): BaseResult<BaseModelResponse> {
        return hotelRemoteDataSource.booking(guest, check_in, check_out, hotel_id)
    }

    suspend fun cancelBooking(
        booking_id: Int
    ): BaseResult<BaseModelResponse> {
        return hotelRemoteDataSource.cancelBooking(booking_id)
    }

    suspend fun getBookingDetail(
        booking_id: Int
    ): BaseResult<BookingDetailResponse> {
        return hotelRemoteDataSource.getBookingDetail(booking_id)
    }

    suspend fun getListMyBooking(
        after: String? = null
    ): BaseResult<MyBookingResponse>{
        return hotelRemoteDataSource.getListMyBooking(after)
    }

}