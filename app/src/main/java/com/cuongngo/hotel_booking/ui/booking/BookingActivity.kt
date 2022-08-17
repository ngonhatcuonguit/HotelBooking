package com.cuongngo.hotel_booking.ui.booking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.AppBaseActivityMVVM
import com.cuongngo.hotel_booking.base.view.onFragmentBackPressed
import com.cuongngo.hotel_booking.base.view.pushFragment
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.databinding.ActivityBookingBinding
import com.cuongngo.hotel_booking.response.HotelModel
import com.cuongngo.hotel_booking.ui.hoteldetail.HotelDetailActivity

class BookingActivity : AppBaseActivityMVVM<ActivityBookingBinding, BookingViewModel>() {

    override val viewModel: BookingViewModel by kodeinViewModel()

    override fun inflateLayout() = R.layout.activity_booking

    private val hotelModel by lazy {
        intent.extras?.getSerializable(HOTEL_KEY) as? HotelModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableLightStatusBar()
    }

    override fun setUp() {
        viewModel.setUpBeforeBookingModel.hotelModel = hotelModel
        viewModel.setUpBeforeBookingModel.hotel_id = hotelModel?.id
        pushFragment(
            SelectDateFragment.instance(viewModel.setUpBeforeBookingModel)
        )
    }

    override fun onBackPressed() {
        if (isIgnoreBackPressed){
            //Do nothing, need customizing after
        }
        else {
            onFragmentBackPressed()
        }
    }

    override fun setUpObserver() {

    }

    companion object{
        val TAG = BookingActivity::class.java.simpleName

        const val HOTEL_KEY = "HOTEL_KEY"
        const val BEFORE_BOOKING_KEY = "BEFORE_BOOKING_KEY"

        fun newIntent(context: Context, hotelModel: HotelModel) =
            Intent(context, BookingActivity::class.java).apply {
                putExtra(
                    HOTEL_KEY, hotelModel
                )
            }
    }
}