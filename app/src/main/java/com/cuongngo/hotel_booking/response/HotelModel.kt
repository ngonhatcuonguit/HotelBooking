package com.cuongngo.hotel_booking.response

data class HotelResponse(
    var success: String?,
    var result_code: Int?,
    var result: String?,
    var cursors: Cursor?,
    var data: HotelData?
): BaseModel()

data class HotelModel(
    val id: Int?,
    var name: String?,
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
    var number_star: Float?,
    var hotel_facility: HotelFacility?,
    var hotel_detail: HotelDetail?
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

data class HotelDetailResponse(
    var success: String?,
    var result_code: Int?,
    var result: String?,
    var cursors: Cursor?,
    var data: HotelModel?
): BaseModel()

data class HotelFacility(
    var id: Int?,
    var hotel_id: Int?,
    var have_swimming: Int?,
    var have_wifi: Int?,
    var have_restaurant: Int?,
    var have_parking: Int?,
    var have_meeting_room: Int?,
    var have_elevator: Int?,
    var have_fitness_center: Int?,
    var have_open: Int?,
    var created_at: String?,
    var updated_at: String?
):BaseModel()

data class HotelDetail(
    var id: Int?,
    var hotel_id: Int?,
    var four_bedrooms: Int?,
    var one_bedrooms: Int?,
    var two_bedrooms: Int?,
    var is_hotel: Int?,
    var two_Bathrooms: Int?,
    var sqft: Int?,
    var created_at: String?,
    var updated_at: String?
) : BaseModel()

data class SetUpBeforeBookingModel(
    var guest: Int? = null,
    var check_in: String? = null,
    var check_out: String? = null,
    var hotel_id: Int? = null,
    var hotelModel: HotelModel? = null
): BaseModel()