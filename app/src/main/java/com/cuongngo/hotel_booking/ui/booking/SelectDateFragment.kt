package com.cuongngo.hotel_booking.ui.booking

import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragment
import com.cuongngo.hotel_booking.base.view.pushFragment
import com.cuongngo.hotel_booking.databinding.FragmentSelectDateBinding

class SelectDateFragment : BaseFragment<FragmentSelectDateBinding>() {

    override fun inflateLayout() = R.layout.fragment_select_date

    override fun setUp() {
        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
        binding.btnContinue.setOnClickListener{
            activity.pushFragment(
                PaymentFragment()
            )
        }
    }

    override fun setUpObserver() {

    }
}