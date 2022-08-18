package com.cuongngo.hotel_booking.response


data class MyBookingResponse(
    val result: String?,
    val result_code: Int?,
    val success: Boolean?,
    var data: ListBooking?
): BaseModel()

data class ListBooking(
    var bookings: List<BookingModel>?
): BaseModel()

data class BookingDetailResponse(
    val result: String?,
    val result_code: Int?,
    val success: Boolean?,
    var data: BookingModel?
): BaseModel()

data class BookingModel(
    var id: Int?,
    var user_id: Int?,
    var hotel_id: Int?,
    var payment_id: Int?,
    var booking_code: String?,
    var check_in: String?,
    var check_out: String?,
    var status: Int?,
    var guest: Int?,
    var created_at: String?,
    var updated_at: String?,
    var number_day: Int?,
    var price: Long?,
    var money_total: Long?,
    var hotel: HotelModel?
): BaseModel()