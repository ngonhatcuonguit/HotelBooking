package com.cuongngo.hotel_booking.services

import com.cuongngo.hotel_booking.response.*
import com.cuongngo.hotel_booking.services.network.invoker.ApiClientFactory
import retrofit2.Response
import retrofit2.http.*

interface HotelApi {

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun signUp(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String,
        @Field("nickname") nickname: String,
        @Field("phone") phone: String,
        @Field("birthday") birthday: String,
        @Field("gender") gender: String
    ): Response<AuthModel>

    @FormUrlEncoded
    @POST("me/change-information")
    suspend fun changeUserInfo(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("nickname") nickname: String,
        @Field("phone") phone: String,
        @Field("birthday") birthday: String,
        @Field("gender") gender: String
    ): Response<DataGetUser>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<UserResponse>

    @POST("me/logout")
    suspend fun logout(): Response<BaseModelResponse>

    @GET("me/information")
    suspend fun getUser(): Response<DataGetUser>

    @GET("home/index")
    suspend fun getListHotel(
        @Query("filtering") filtering: String? = null,
        @Query("city_id") city_id: String? = null,
        @Query("count") count: String? = null,
        @Query("after") after: String? = null,
        @Query("name") name: String? = null
    ): Response<HotelResponse>

    @GET("hotel/{hotel_id}")
    suspend fun getDetailHotel(
        @Path("hotel_id") hotel_id: Int
    ): Response<HotelDetailResponse>

    @FormUrlEncoded
    @POST("booking/hotel")
    suspend fun booking(
        @Field("guest") guest: Int,
        @Field("check_in") check_in: String,
        @Field("check_out") check_out: String,
        @Field("hotel_id") hotel_id: Int,
        @Field("payment_id") payment_id: Int = 1
    ): Response<BaseModelResponse>

    companion object {
        operator fun invoke(): HotelApi {
            return ApiClientFactory.createService()
        }
    }
}