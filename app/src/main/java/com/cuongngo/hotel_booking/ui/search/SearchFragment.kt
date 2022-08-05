package com.cuongngo.hotel_booking.ui.search

import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragment
import com.cuongngo.hotel_booking.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun inflateLayout() = R.layout.fragment_search

    override fun setUp() {}

    override fun setUpObserver() {}

    companion object {
        val TAG = SearchFragment::class.java.simpleName
    }

}