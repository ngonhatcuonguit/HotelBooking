package com.cuongngo.hotel_booking.services.remote

import com.cuongngo.hotel_booking.services.HotelApi
import com.cuongngo.hotel_booking.services.network.BaseRemoteDataSource

class UserRemoteDataSource(private val apiService: HotelApi) : BaseRemoteDataSource() {
    suspend fun signUp(
        name: String,
        email: String,
        password: String,
        password_confirmation: String,
        nickname: String,
        phone: String,
        birthday: String,
        gender: String
    ) = getResult {
        apiService.signUp(
            name, email, password, password_confirmation, nickname, phone, birthday, gender
        )
    }

    suspend fun changeUserInfo(
        name: String,
        email: String,
        nickname: String,
        phone: String,
        birthday: String,
        gender: String
    ) = getResult {
        apiService.changeUserInfo(
            name, email, nickname, phone, birthday, gender
        )
    }

    suspend fun login(
        email: String,
        password: String
    ) = getResult {
        apiService.login(email, password)
    }

    suspend fun logout() = getResult {
        apiService.logout()
    }

    suspend fun getUser() = getResult {
        apiService.getUser()
    }

}