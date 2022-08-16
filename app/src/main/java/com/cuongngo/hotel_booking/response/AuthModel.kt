package com.cuongngo.hotel_booking.response

data class AuthModel(
    val result: String?,
    val result_code: Int?,
    var cursors: Cursor?,
    val success: Boolean?,
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
    val result: String?,
    val result_code: Int?,
    var cursors: Cursor?,
    val success: Boolean?,
    var data: DataUser
): BaseModel()

data class DataUser(
    var access_token: String?,
    var token_type: String?,
    var expires_in: Long?,
    var user: UserModel?
):BaseModel()

data class DataGetUser(
    val result: String?,
    val result_code: Int?,
    var cursors: Cursor?,
    val success: Boolean?,
    var data: UserModel?
):BaseModel()

data class UserModel(
    var id: Int?,
    var name: String?,
    var access_token: String?,
    var email: String?,
    var phone: String?,
    var nickname: String?,
    var birthday: String?,
    var gender: Int?,
    var updated_at: String?,
    var created_at: String?,
    var status: Int?,
    var email_verified_at: String?,
    var avatar: String?
): BaseModel()