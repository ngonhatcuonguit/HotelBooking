package com.cuongngo.hotel_booking.ui.home

import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragment
import com.cuongngo.hotel_booking.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun inflateLayout() = R.layout.fragment_home

    override fun setUp() {

    }

    override fun setUpObserver() { }

    companion object {
        val TAG = HomeFragment::class.java.simpleName
    }

}