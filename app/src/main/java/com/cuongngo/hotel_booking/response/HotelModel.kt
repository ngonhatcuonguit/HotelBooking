package com.cuongngo.hotel_booking.response

data class HotelResponse(
    var success: String?,
    var result_code: Int?,
    var result: String?,
    var data: HotelData?
)

data class HotelModel(
    val id: Int?,
    var vote_average: Float?,
    var name: String?,
    var location: String?,
    var price: Long?,
    var description: String?,
    var address: String?,
    var is_recommand: Int?,
    var is_popular: Int?,
    var is_trending: Int?,
    var city_id: Int?,
    var district_id: Int?,
    var ward_id: Int?,
    var status: Int?,
    var created_at: String?,
    var updated_at: String?,
    var stock: Int?,
    var sales_volume: Int?,
    var city: City?,
    var district: City?,
    var ward: City?,
    var images: List<Image>?,
    var view: Long?,
    var number_star: Float?
): BaseModel()

data class City(
    var id: Int?,
    var code: String?,
    var desc: String?,
    var name: String?,
    var province_code: String?,
    var order: Int?
): BaseModel()

data class Image(
    var id: Int?,
    var hotel_id: Int?,
    var label: String?,
    var small: String?,
    var medium: String?,
    var large: String?,
    var fhd: String?,
    var original: String?,
    var created_at: String?,
    var updated_at: String?
): BaseModel()

data class HotelData(
    var hotels: List<HotelModel>?
)