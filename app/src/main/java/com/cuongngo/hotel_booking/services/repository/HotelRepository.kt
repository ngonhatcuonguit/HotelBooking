package com.cuongngo.hotel_booking.services.repository

import com.cuongngo.hotel_booking.response.BaseModelResponse
import com.cuongngo.hotel_booking.response.HotelDetailResponse
import com.cuongngo.hotel_booking.response.HotelResponse
import com.cuongngo.hotel_booking.services.network.BaseResult
import com.cuongngo.hotel_booking.services.remote.HotelRemoteDataSource

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

}