package com.cuongngo.hotel_booking.ui.mybooking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cuongngo.hotel_booking.base.viewmodel.BaseViewModel
import com.cuongngo.hotel_booking.response.BaseModelResponse
import com.cuongngo.hotel_booking.response.MyBookingResponse
import com.cuongngo.hotel_booking.services.network.BaseResult
import com.cuongngo.hotel_booking.services.repository.HotelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyBookingViewModel(private val hotelRepository: HotelRepository): BaseViewModel() {

    private var _listMyBooking = MutableLiveData<BaseResult<MyBookingResponse>>()
    val listMyBooking: LiveData<BaseResult<MyBookingResponse>> = _listMyBooking

    private var _cancelBooking = MutableLiveData<BaseResult<BaseModelResponse>>()
    val cancelBooking: LiveData<BaseResult<BaseModelResponse>> = _cancelBooking

    var after: String? = null

    fun getListMyBooking() {
        _listMyBooking.value = BaseResult.loading(null)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _listMyBooking.postValue(
                    hotelRepository.getListMyBooking(after)
                )
            }
        }
    }

    fun cancelBooking(
        booking_id: Int
    ) {
        _cancelBooking.value = BaseResult.loading(null)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _cancelBooking.postValue(
                    hotelRepository.cancelBooking(booking_id)
                )
            }
        }
    }

    fun loadMoreListBooking() {
        after?.let {
            getListMyBooking()
        }
    }

}