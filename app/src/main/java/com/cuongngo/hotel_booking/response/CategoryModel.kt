package com.cuongngo.hotel_booking.response

data class CategoryModel(
    val id: Int?,
    val name: String?,
    var is_selected: Boolean = false
) : BaseModel()