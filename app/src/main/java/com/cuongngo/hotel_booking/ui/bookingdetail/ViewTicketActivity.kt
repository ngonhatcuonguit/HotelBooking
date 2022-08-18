package com.cuongngo.hotel_booking.ui.bookingdetail

import android.content.Context
import android.content.Intent
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.AppBaseActivityMVVM
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.databinding.ActivityViewTicketBinding
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.local.AppPreferences
import com.cuongngo.hotel_booking.services.network.onResultReceived

class ViewTicketActivity :
    AppBaseActivityMVVM<ActivityViewTicketBinding, BookingDetailViewModel>() {

    override val viewModel: BookingDetailViewModel by kodeinViewModel()

    override fun inflateLayout() = R.layout.activity_view_ticket

    private val bookingID by lazy {
        intent.extras?.getInt(BOOKING_ID_KEY)
    }

    override fun setUp() {

        viewModel.bookingDetail(bookingID ?: return)

        with(binding) {
            ivBack.setOnClickListener {
                onBackPressed()
            }
            btnOk.setOnClickListener {
                onBackPressed()
            }
            tvPhone.text = AppPreferences.getPhone()
            tvName.text = AppPreferences.getNickName()
        }
    }

    override fun setUpObserver() {
        observeLiveDataChanged(viewModel.bookingDetail){
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    binding.booking = it.data?.data
                },
                onError = {

                }
            )
        }
    }

    companion object {
        val TAG = ViewTicketActivity::class.java.simpleName

        const val BOOKING_ID_KEY = "BOOKING_ID_KEY"

        fun newIntent(context: Context, booking_id: Int?) =
            Intent(context, ViewTicketActivity::class.java).apply {
                putExtra(
                    BOOKING_ID_KEY, booking_id
                )
            }
    }
}