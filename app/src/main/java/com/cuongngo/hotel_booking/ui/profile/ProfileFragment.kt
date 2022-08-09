package com.cuongngo.hotel_booking.ui.profile

import android.content.Intent
import com.cuongngo.cinemax.utils.Constants
import com.cuongngo.cinemax.utils.Constants.Key.Companion.ACTION
import com.cuongngo.cinemax.utils.Constants.Key.Companion.EDIT_PROFILE
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragment
import com.cuongngo.hotel_booking.databinding.FragmentProfileBinding
import com.cuongngo.hotel_booking.ui.auth.SignUpActivity

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun inflateLayout() = R.layout.fragment_profile

    override fun setUp() {
        with(binding){
            tvEditProfile.setOnClickListener {
                startActivity(Intent(context, SignUpActivity::class.java).apply {
                    putExtra(ACTION, EDIT_PROFILE)
                })
            }
            tvLogout.setOnClickListener {
                LogoutBottomSheetFragment().show(childFragmentManager, LogoutBottomSheetFragment.TAG)
            }
        }
    }

    override fun setUpObserver() { }

    companion object {
        val TAG = ProfileFragment::class.java.simpleName
    }
}