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
    ): BaseResult<AuthModel> {
        return userRemoteDataSource.signUp(
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
    ): BaseResult<DataGetUser> {
        return userRemoteDataSource.changeUserInfo(
            name, email, nickname, phone, birthday, gender
        )
    }

    suspend fun changePassword(
        email: String,
        password: String,
        password_confirmation: String
    ): BaseResult<DataGetUser>{
        return userRemoteDataSource.changePassword(
            email, password, password_confirmation
        )
    }

    suspend fun login(
        email: String,
        password: String
    ): BaseResult<UserResponse> {
        return userRemoteDataSource.login(email, password)
    }

    suspend fun logout() : BaseResult<BaseModelResponse> {
        return userRemoteDataSource.logout()
    }
    suspend fun getUser() : BaseResult<DataGetUser> {
        return userRemoteDataSource.getUser()
    }

}