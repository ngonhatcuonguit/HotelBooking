package com.cuongngo.hotel_booking.ui.booking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cuongngo.hotel_booking.base.viewmodel.BaseViewModel
import com.cuongngo.hotel_booking.response.BaseModelResponse
import com.cuongngo.hotel_booking.response.SetUpBeforeBookingModel
import com.cuongngo.hotel_booking.services.network.BaseResult
import com.cuongngo.hotel_booking.services.repository.HotelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookingViewModel(private val hotelRepository: HotelRepository): BaseViewModel() {

    private var _booking = MutableLiveData<BaseResult<BaseModelResponse>>()
    val booking: LiveData<BaseResult<BaseModelResponse>> = _booking

    var setUpBeforeBookingModel = SetUpBeforeBookingModel()

    fun booking(
        guest: Int,
        check_in: String,
        check_out: String,
        hotel_id: Int
    ){
        _booking.value = BaseResult.loading(null)
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _booking.postValue(
                    hotelRepository.booking(guest, check_in, check_out, hotel_id)
                )
            }
        }
    }

}