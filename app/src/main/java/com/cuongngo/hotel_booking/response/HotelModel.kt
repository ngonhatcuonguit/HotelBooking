package com.cuongngo.hotel_booking.response

data class HotelModel(
    val id: Int?,
    var vote_average: Float?,
    var name: String?,
    var location: String?,
    var price: Long?,
    var image: String?,
    var view: Long?
): BaseModel()
