package com.cuongngo.hotel_booking.response

//data class BaseResponse<T> (
//    val result: String?,
//    val result_code: Int?,
//    var cursors: Cursor?,
//    val success: Boolean?,
//    val data: T?
//): BaseModel()

data class BaseModelResponse(
    val result: String?,
    val result_code: Int?,
    val success: Boolean?
): BaseModel()

data class Cursor(val before:String? , val after:String?): BaseModel()
