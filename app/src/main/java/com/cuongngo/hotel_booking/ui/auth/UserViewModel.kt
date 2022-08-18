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

    private var _signUp = MutableLiveData<BaseResult<AuthModel>>()
    val signUp: LiveData<BaseResult<AuthModel>> = _signUp

    private var _changeInfo = MutableLiveData<BaseResult<DataGetUser>>()
    val changeInfo: LiveData<BaseResult<DataGetUser>> = _changeInfo

    private var _login = MutableLiveData<BaseResult<UserResponse>>()
    val login: LiveData<BaseResult<UserResponse>> = _login

    private var _logout = MutableLiveData<BaseResult<BaseModelResponse>>()
    val logout: LiveData<BaseResult<BaseModelResponse>> = _logout

    private var _getUser = MutableLiveData<BaseResult<DataGetUser>>()
    val getUser: LiveData<BaseResult<DataGetUser>> = _getUser

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
        _signUp.value = BaseResult.loading(null)
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

    fun changeInfo(
        name: String,
        email: String,
        nickname: String,
        phone: String,
        birthday: String,
        gender: String
    ) {
        _changeInfo.value = BaseResult.loading(null)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _changeInfo.postValue(
                    userRepository.changeUserInfo(
                        name,
                        email,
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
        _login.value = BaseResult.loading(null)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _login.postValue(
                    userRepository.login(email, password)
                )
            }
        }
    }

    fun logOut() {
        _logout.value = BaseResult.loading(null)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _logout.postValue(
                    userRepository.logout()
                )
            }
        }
    }

    fun getUser() {
        _getUser.value = BaseResult.loading(null)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _getUser.postValue(
                    userRepository.getUser()
                )
            }
        }
    }

}