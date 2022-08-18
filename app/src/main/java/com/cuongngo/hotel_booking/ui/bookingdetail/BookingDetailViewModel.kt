package com.cuongngo.hotel_booking.ui.bookingdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cuongngo.hotel_booking.base.viewmodel.BaseViewModel
import com.cuongngo.hotel_booking.response.BookingDetailResponse
import com.cuongngo.hotel_booking.services.network.BaseResult
import com.cuongngo.hotel_booking.services.repository.HotelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookingDetailViewModel(private val hotelRepository: HotelRepository): BaseViewModel() {

    private var _bookingDetail = MutableLiveData<BaseResult<BookingDetailResponse>>()
    val bookingDetail : LiveData<BaseResult<BookingDetailResponse>> = _bookingDetail


    fun bookingDetail(booking_id: Int){
        _bookingDetail.value = BaseResult.loading(null)
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _bookingDetail.postValue(hotelRepository.getBookingDetail(booking_id))
            }
        }
    }

}