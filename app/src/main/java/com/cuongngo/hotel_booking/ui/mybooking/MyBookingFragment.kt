package com.cuongngo.hotel_booking.ui.mybooking

import android.graphics.Color
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragmentMVVM
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.databinding.FragmentMyBookingBinding
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.response.HotelModel
import com.cuongngo.hotel_booking.services.network.onResultReceived

class MyBookingFragment : BaseFragmentMVVM<FragmentMyBookingBinding, MyBookingViewModel>() {

    override val viewModel: MyBookingViewModel by kodeinViewModel()

    private lateinit var myBookingAdapter: MyBookingAdapter
    var listHotel = arrayListOf<HotelModel>()
    private var filter = 0

    override fun inflateLayout() = R.layout.fragment_my_booking

    override fun setUp() {
        viewModel.getListMyBooking()
        setupRcvHotel()

        with(binding){
            tvOngoing.setOnClickListener {
                handleChooseFilter(0)
            }
            tvComplete.setOnClickListener {
                handleChooseFilter(1)
            }
            tvCancel.setOnClickListener {
                handleChooseFilter(2)
            }
        }
    }

    override fun setUpObserver() {
        observeLiveDataChanged(viewModel.listMyBooking){
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    if (it.data?.result_code == 1){
                        myBookingAdapter.submitListBooking(it.data.data?.bookings.orEmpty())
                    }
                },
                onError = {
                    hideProgressDialog()
                }
            )
        }
    }

    private fun setupRcvHotel(){
        myBookingAdapter = MyBookingAdapter(
            arrayListOf()
        )
        binding.rcvListMyBooking.adapter = myBookingAdapter

    }

    private fun handleChooseFilter(value: Int){
        if (value != filter){
            filter = value
            when (filter){
                0 -> {
                    binding.tvOngoing.setBackgroundColor(Color.parseColor("#1AB65C"))
                    binding.tvOngoing.setTextColor(Color.parseColor("#FFFFFF"))

                    binding.tvComplete.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    binding.tvComplete.setTextColor(Color.parseColor("#1AB65C"))

                    binding.tvCancel.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    binding.tvCancel.setTextColor(Color.parseColor("#1AB65C"))

                }
                1 -> {
                    binding.tvOngoing.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    binding.tvOngoing.setTextColor(Color.parseColor("#1AB65C"))

                    binding.tvComplete.setBackgroundColor(Color.parseColor("#1AB65C"))
                    binding.tvComplete.setTextColor(Color.parseColor("#FFFFFF"))

                    binding.tvCancel.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    binding.tvCancel.setTextColor(Color.parseColor("#1AB65C"))
                }

                2 -> {
                    binding.tvOngoing.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    binding.tvOngoing.setTextColor(Color.parseColor("#1AB65C"))

                    binding.tvComplete.setBackgroundColor(Color.parseColor("#FFFFFF"))
                    binding.tvComplete.setTextColor(Color.parseColor("#1AB65C"))

                    binding.tvCancel.setBackgroundColor(Color.parseColor("#1AB65C"))
                    binding.tvCancel.setTextColor(Color.parseColor("#FFFFFF"))
                }
            }
        }

    }

    companion object {
        val TAG = MyBookingFragment::class.java.simpleName
    }
}