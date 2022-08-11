package com.cuongngo.hotel_booking.services

import com.cuongngo.hotel_booking.response.AuthModel
import com.cuongngo.hotel_booking.response.BaseResponse
import com.cuongngo.hotel_booking.response.UserModel
import com.cuongngo.hotel_booking.services.network.invoker.ApiClientFactory
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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
    ): Response<BaseResponse<AuthModel>>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<BaseResponse<UserModel>>

    companion object {
        operator fun invoke(): HotelApi {
            return ApiClientFactory.createService()
        }
    }
}