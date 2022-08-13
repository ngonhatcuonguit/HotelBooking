package com.cuongngo.hotel_booking.ui.profile

import android.content.Intent
import com.cuongngo.hotel_booking.utils.Constants.Key.Companion.ACTION
import com.cuongngo.hotel_booking.utils.Constants.Key.Companion.EDIT_PROFILE
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragment
import com.cuongngo.hotel_booking.base.fragment.BaseFragmentMVVM
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.databinding.FragmentProfileBinding
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.local.AppPreferences
import com.cuongngo.hotel_booking.services.network.onResultReceived
import com.cuongngo.hotel_booking.ui.auth.SignUpActivity
import com.cuongngo.hotel_booking.ui.auth.UserViewModel

class ProfileFragment : BaseFragmentMVVM<FragmentProfileBinding, UserViewModel>(), LogoutBottomSheetFragment.LogoutListener {

    override val viewModel: UserViewModel by kodeinViewModel()

    override fun inflateLayout() = R.layout.fragment_profile

    override fun setUp() {
        with(binding){
            tvEditProfile.setOnClickListener {
                startActivity(Intent(context, SignUpActivity::class.java).apply {
                    putExtra(ACTION, EDIT_PROFILE)
                })
            }
            tvLogout.setOnClickListener {
                LogoutBottomSheetFragment(this@ProfileFragment).show(childFragmentManager, LogoutBottomSheetFragment.TAG)
            }

        }
    }

    override fun onLogoutSelected() {
        viewModel.logOut()
    }

    override fun setUpObserver() {
        observeLiveDataChanged(viewModel.logout){
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    AppPreferences.setNickName("")
                    AppPreferences.setUserAccessToken("")
                    showDialog("Chú ý", message = "Bạn đã logout thành công")
                },
                onError = {
                    showDialog("Chú ý", message = it.data?.result)
                }
            )
        }

    }

    companion object {
        val TAG = ProfileFragment::class.java.simpleName
    }
}