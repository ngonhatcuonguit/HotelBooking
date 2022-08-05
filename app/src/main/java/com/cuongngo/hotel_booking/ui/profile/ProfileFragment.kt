package com.cuongngo.hotel_booking.ui.profile

import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragment
import com.cuongngo.hotel_booking.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun inflateLayout() = R.layout.fragment_profile

    override fun setUp() {

    }

    override fun setUpObserver() { }

    companion object {
        val TAG = ProfileFragment::class.java.simpleName
    }
}