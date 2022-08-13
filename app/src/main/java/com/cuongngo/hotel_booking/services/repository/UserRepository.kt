package com.cuongngo.hotel_booking.services.repository

import com.cuongngo.hotel_booking.response.*
import com.cuongngo.hotel_booking.services.network.BaseResult
import com.cuongngo.hotel_booking.services.remote.UserRemoteDataSource

class UserRepository(private val userRemoteDataSource: UserRemoteDataSource) {

    suspend fun signUp(
        name: String,
        email: String,
        password: String,
        password_confirmation: String,
        nickname: String,
        phone: String,
        birthday: String,
        gender: String
    ): BaseResult<BaseResponse<AuthModel>> {
        return userRemoteDataSource.signUp(
            name, email, password, password_confirmation, nickname, phone, birthday, gender
        )
    }

    suspend fun login(
        email: String,
        password: String
    ): BaseResult<BaseResponse<UserResponse>> {
        return userRemoteDataSource.login(email, password)
    }

    suspend fun logout() : BaseResult<BaseModelResponse> {
        return userRemoteDataSource.logout()
    }
    suspend fun getUser() : BaseResult<BaseResponse<UserModel>> {
        return userRemoteDataSource.getUser()
    }

}