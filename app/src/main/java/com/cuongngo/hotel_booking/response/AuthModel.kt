package com.cuongngo.hotel_booking.response

data class AuthModel(
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

data class UserResponse(
    var access_token: String?,
    var token_type: String?,
    var expires_in: Long?,
    var user: UserModel?
)

data class UserModel(
    var id: Int?,
    var name: String?,
    var email: String?,
    var phone: String?,
    var nickname: String?,
    var birthday: String?,
    var gender: String?,
    var updated_at: String?,
    var created_at: String?,
    var status: String?,
    var email_verified_at: String?,
    var avatar: String?
): BaseModel()