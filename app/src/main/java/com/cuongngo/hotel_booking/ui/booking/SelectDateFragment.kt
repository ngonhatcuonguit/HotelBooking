package com.cuongngo.hotel_booking.ui.booking

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.core.os.bundleOf
import carbon.widget.TextView
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.AppBaseActivityMVVM
import com.cuongngo.hotel_booking.base.fragment.BaseFragment
import com.cuongngo.hotel_booking.base.fragment.BaseFragmentMVVM
import com.cuongngo.hotel_booking.base.view.pushFragment
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModelFromActivity
import com.cuongngo.hotel_booking.databinding.FragmentSelectDateBinding
import com.cuongngo.hotel_booking.response.HotelModel
import com.cuongngo.hotel_booking.response.SetUpBeforeBookingModel
import com.cuongngo.hotel_booking.ui.booking.BookingActivity.Companion.BEFORE_BOOKING_KEY
import com.cuongngo.hotel_booking.ui.booking.BookingActivity.Companion.HOTEL_KEY
import com.cuongngo.hotel_booking.utils.formatToPrice
import com.cuongngo.hotel_booking.utils.getDateString
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

class SelectDateFragment : BaseFragmentMVVM<FragmentSelectDateBinding, BookingViewModel>() {

    override val viewModel: BookingViewModel by kodeinViewModelFromActivity()

    override fun inflateLayout() = R.layout.fragment_select_date

    private var startCalendar: Calendar = Calendar.getInstance()
    private var endCalendar: Calendar = Calendar.getInstance()
    private var gusest: Long? = null

    private val beforeBookingModel by lazy {
        arguments?.getSerializable(BEFORE_BOOKING_KEY) as? SetUpBeforeBookingModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        endCalendar.add(Calendar.DATE, 1)
    }

    override fun setUp() {
        updateDay()

        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.btnContinue.setOnClickListener {
            activity.pushFragment(
                PaymentFragment.instance(viewModel.setUpBeforeBookingModel)
            )
        }
        binding.calenderView.setOnClickListener {
            rangeDatePicker()
        }

        binding.tvDateCheckIn.setOnClickListener {
            rangeDatePicker()
        }

        binding.tvCheckOut.setOnClickListener {
            rangeDatePicker()
        }

        binding.tvAddDate.setOnClickListener {
            endCalendar.add(Calendar.DATE, 1)
            updateDay()
        }
        binding.tvMinusDate.setOnClickListener {
            if (gusest != 0L) {
                endCalendar.add(Calendar.DATE, -1)
                updateDay()
            }
        }
    }

    override fun setUpObserver() {

    }

    private fun rangeDatePicker() {
        Locale.setDefault(Locale("vi"))
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        builder.setTheme(R.style.CustomThemeOverlay_MaterialCalendar_Fullscreen)
        builder.setTitleText(getString(R.string.setup_date_booking))
        val picker = builder.build()
        picker.addOnPositiveButtonClickListener {
            val dateResultStart = Date(it.first ?: System.currentTimeMillis())
            val dateResultEnd = Date(it.second ?: System.currentTimeMillis())
            startCalendar.time = dateResultStart
            endCalendar.time = dateResultEnd
            updateDay()
        }
        picker.show(childFragmentManager, picker.toString())
    }


    private fun updateDay(){
        val total = viewModel.setUpBeforeBookingModel.hotelModel?.price?.times(gusest?.toInt() ?: 1)
        binding.tvDateCheckIn.text = startCalendar.getDateString()
        binding.tvCheckOut.text = endCalendar.getDateString()
        countDate()
        viewModel.setUpBeforeBookingModel.check_in = startCalendar.getDateString()
        viewModel.setUpBeforeBookingModel.check_out = endCalendar.getDateString()
        binding.tvTotal.text = "Total: " + total?.formatToPrice() + "đ"
    }

    private fun countDate() {
        try {
            // Convert `String` to `Date`
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val dateBefore = sdf.parse(startCalendar.getDateString())
            val dateAfter = sdf.parse(endCalendar.getDateString())

            // Calculate the number of days between dates
            val timeDiff = abs(dateAfter.time - dateBefore.time)
            val daysDiff: Long = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS)
            gusest = daysDiff
            binding.tvNumberOfDate.text = gusest.toString()
            viewModel.setUpBeforeBookingModel.guest = daysDiff.toInt()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    companion object {
        val TAG = SelectDateFragment::class.java.simpleName

        fun instance(
            beforeBookingModel: SetUpBeforeBookingModel?
        ): SelectDateFragment {
            return SelectDateFragment().apply {
                arguments = bundleOf().apply {
                    putSerializable(BEFORE_BOOKING_KEY, beforeBookingModel)
                }
            }
        }
    }

}