package com.cuongngo.hotel_booking.ui.hoteldetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cuongngo.hotel_booking.base.viewmodel.BaseViewModel
import com.cuongngo.hotel_booking.response.HotelDetailResponse
import com.cuongngo.hotel_booking.services.network.BaseResult
import com.cuongngo.hotel_booking.services.repository.HotelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HotelDetailViewModel(private val hotelRepository: HotelRepository): BaseViewModel() {

    private var _hotelDetail = MutableLiveData<BaseResult<HotelDetailResponse>>()
    val hotelDetail : LiveData<BaseResult<HotelDetailResponse>> = _hotelDetail

    fun getDetailHotel(hotel_id: Int){
        _hotelDetail.value = BaseResult.loading(null)
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _hotelDetail.postValue(hotelRepository.getDetailHotel(hotel_id))
            }
        }
    }

}