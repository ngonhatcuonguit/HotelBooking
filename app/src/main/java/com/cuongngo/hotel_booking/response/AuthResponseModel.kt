package com.cuongngo.hotel_booking.response

data class AuthResponseModel(
    var id: Int?,
    var name: String?,
    var email: String?,
    var phone: String?,
    var nickname: String?,
    var birthday: String?,
    var gender: String?,
    var updated_at: String?,
    var created_at: String?
): BaseModel()