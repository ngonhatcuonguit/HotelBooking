package com.cuongngo.hotel_booking.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cuongngo.hotel_booking.base.viewmodel.BaseViewModel
import com.cuongngo.hotel_booking.response.CategoryModel
import com.cuongngo.hotel_booking.response.HotelResponse
import com.cuongngo.hotel_booking.services.network.BaseResult
import com.cuongngo.hotel_booking.services.repository.HotelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val hotelRepository: HotelRepository) : BaseViewModel() {

    private var _listHotel = MutableLiveData<BaseResult<HotelResponse>>()
    val listHotel : LiveData<BaseResult<HotelResponse>> = _listHotel

    private var _categoryFilter = MutableLiveData<CategoryModel>(CategoryModel(
        1,
        "All Hotel",
        "empty ",
        false
    ))

    val categoryFilter: LiveData<CategoryModel?> get() = _categoryFilter

    fun updateFilter(newCategory: CategoryModel){
        this._categoryFilter.value = newCategory
        filtering = newCategory.param
    }

    var after: String? = null
    var name: String? = null
    var filtering: String? = null

    fun getListHotel(
        city_id: String? = null,
        count: String? = null
    ){
        _listHotel.value = BaseResult.loading(null)
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                _listHotel.postValue(hotelRepository.getListHotel(filtering, city_id, count, after, name))
            }
        }
    }

    fun loadMoreListHotel(){
        after?.let {
            getListHotel()
        }
    }

}