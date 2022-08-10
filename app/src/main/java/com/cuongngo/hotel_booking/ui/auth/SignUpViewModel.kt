package com.cuongngo.hotel_booking.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cuongngo.hotel_booking.base.viewmodel.BaseViewModel
import com.cuongngo.hotel_booking.response.AuthResponseModel
import com.cuongngo.hotel_booking.response.BaseResponse
import com.cuongngo.hotel_booking.services.network.BaseResult
import com.cuongngo.hotel_booking.services.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    private var _signUp = MutableLiveData<BaseResult<BaseResponse<AuthResponseModel>>>()
    val signUp: LiveData<BaseResult<BaseResponse<AuthResponseModel>>> = _signUp

    fun signUp(
        name: String,
        email: String,
        password: String,
        password_confirmation: String,
        nickname: String,
        phone: String,
        birthday: String,
        gender: String
    ){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _signUp.postValue(
                    userRepository.signUp(
                        name, email, password, password_confirmation, nickname, phone, birthday, gender
                    )
                )
            }
        }
    }

}