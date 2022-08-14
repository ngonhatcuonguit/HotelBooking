package com.cuongngo.hotel_booking.ui.home

import android.content.Intent
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.AppBaseActivityMVVM
import com.cuongngo.hotel_booking.base.activity.BaseActivity
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.databinding.ActivityHomeIndexBinding
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.response.HotelModel
import com.cuongngo.hotel_booking.services.network.onResultReceived
import com.cuongngo.hotel_booking.ui.hoteldetail.HotelDetailActivity

class HomeIndexActivity : AppBaseActivityMVVM<ActivityHomeIndexBinding, HomeViewModel>(), HotelAdapter.SelectedListener {

    override val viewModel: HomeViewModel by kodeinViewModel()

    override fun inflateLayout() = R.layout.activity_home_index

    private lateinit var hotelAdapter: HotelAdapter

    override fun setUp() {
        with(binding){
            ivBack.setOnClickListener {
                onBackPressed()
            }
        }
        viewModel.getListHotel()
    }

    private fun setupRcvHotel(listHotel: List<HotelModel>){
        hotelAdapter = HotelAdapter(
            arrayListOf(),
            this
        )
        hotelAdapter.submitListHotel(listHotel.toList())
        binding.rcvListHotel.adapter = hotelAdapter

    }

    override fun onHotelSelectedListener(hotelModel: HotelModel) {
        startActivity(Intent(this, HotelDetailActivity::class.java))
    }

    override fun setUpObserver() {
        observeLiveDataChanged(viewModel.listHotel){
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    setupRcvHotel(it.data?.data?.hotels.orEmpty())
                },
                onError = {
                    hideProgressDialog()
                }
            )
        }
    }

    companion object {
        val TAG = HomeIndexActivity::class.java.simpleName
    }
}