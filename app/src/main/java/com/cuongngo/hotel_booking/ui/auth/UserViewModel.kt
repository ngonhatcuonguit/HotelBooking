package com.cuongngo.hotel_booking.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cuongngo.hotel_booking.base.viewmodel.BaseViewModel
import com.cuongngo.hotel_booking.response.*
import com.cuongngo.hotel_booking.services.network.BaseResult
import com.cuongngo.hotel_booking.services.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    private var _signUp = MutableLiveData<BaseResult<BaseResponse<AuthModel>>>()
    val signUp: LiveData<BaseResult<BaseResponse<AuthModel>>> = _signUp

    private var _login = MutableLiveData<BaseResult<BaseResponse<UserResponse>>>()
    val login: LiveData<BaseResult<BaseResponse<UserResponse>>> = _login

    private var _logout = MutableLiveData<BaseResult<BaseModelResponse>>()
    val logout: LiveData<BaseResult<BaseModelResponse>> = _logout

    private var _getUser = MutableLiveData<BaseResult<BaseResponse<UserModel>>>()
    val getUser: LiveData<BaseResult<BaseResponse<UserModel>>> = _getUser

    fun signUp(
        name: String,
        email: String,
        password: String,
        password_confirmation: String,
        nickname: String,
        phone: String,
        birthday: String,
        gender: String
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _signUp.postValue(
                    userRepository.signUp(
                        name,
                        email,
                        password,
                        password_confirmation,
                        nickname,
                        phone,
                        birthday,
                        gender
                    )
                )
            }
        }
    }

    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _login.postValue(
                    userRepository.login(email, password)
                )
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _logout.postValue(
                    userRepository.logout()
                )
            }
        }
    }

    fun getUser() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _getUser.postValue(
                    userRepository.getUser()
                )
            }
        }
    }

}