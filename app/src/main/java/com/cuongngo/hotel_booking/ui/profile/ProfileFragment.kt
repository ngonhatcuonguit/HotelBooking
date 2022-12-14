package com.cuongngo.hotel_booking.ui.profile

import android.content.Intent
import com.cuongngo.hotel_booking.utils.Constants.Key.Companion.ACTION
import com.cuongngo.hotel_booking.utils.Constants.Key.Companion.EDIT_PROFILE
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragment
import com.cuongngo.hotel_booking.base.fragment.BaseFragmentMVVM
import com.cuongngo.hotel_booking.base.view.DialogUtils
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.databinding.FragmentProfileBinding
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.local.AppPreferences
import com.cuongngo.hotel_booking.services.network.onResultReceived
import com.cuongngo.hotel_booking.ui.auth.LoginActivity
import com.cuongngo.hotel_booking.ui.auth.SignUpActivity
import com.cuongngo.hotel_booking.ui.auth.UserViewModel

class ProfileFragment : BaseFragmentMVVM<FragmentProfileBinding, UserViewModel>(), LogoutBottomSheetFragment.LogoutListener {

    override val viewModel: UserViewModel by kodeinViewModel()

    override fun inflateLayout() = R.layout.fragment_profile

    override fun setUp() {
        with(binding){
            if (AppPreferences.getUserAccessToken().isNullOrEmpty()){
                tvName.text = "Login to booking now"
                email.text = "example1123@gmail.com"
                tvName.setOnClickListener {
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                }
            }else {
                tvName.text = AppPreferences.getNickName()
                email.text = AppPreferences.getEmail()
            }
            tvEditProfile.setOnClickListener {
                startActivity(Intent(context, SignUpActivity::class.java).apply {
                    putExtra(ACTION, EDIT_PROFILE)
                })
            }

            imgAvatar.setOnClickListener {
                startActivity(Intent(context,LoginActivity::class.java))
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
                    when(it.data?.result_code){
                        1 -> {
                            AppPreferences.setNickName("")
                            AppPreferences.setEmail("")
                            AppPreferences.setUserAccessToken("")
                            showDialog("Ch?? ??", message = "B???n ???? logout th??nh c??ng")
                        }
                        300,301,302 -> {

                            showDialog(
                                title = "Ch?? ??",
                                message = it.data.result,
                                isCancelAble = false,
                                onDialogButtonClick = object : DialogUtils.DialogOnClickListener {
                                    override fun onClick(isPositiveClick: Boolean) {
                                        startActivity(Intent(requireContext(), LoginActivity::class.java))
                                    }
                                } )
                        }
                        else -> {
                            showDialog(title = "Ch?? ??", message = it.data?.result)
                        }
                    }
                },
                onError = {
                    showDialog("Ch?? ??", message = it.data?.result)
                }
            )
        }

        observeLiveDataChanged(viewModel.getUser){
            it.onResultReceived(
                onLoading = { },
                onSuccess = {
                    when(it.data?.result_code){
                        1 -> {
                            binding.tvName.text = it.data.data?.nickname
                            binding.email.text = it.data.data?.email
                        }
                        300,301,302 -> {

                            showDialog(
                                title = "Ch?? ??",
                                message = it.data.result,
                                isCancelAble = false,
                                onDialogButtonClick = object : DialogUtils.DialogOnClickListener {
                                    override fun onClick(isPositiveClick: Boolean) {
                                        startActivity(Intent(requireContext(), LoginActivity::class.java))
                                    }
                                } )
                        }
                        else -> {
                            showDialog(title = "Ch?? ??", message = it.data?.result)
                        }
                    }
                },
                onError = {
                    showDialog("Ch?? ??", message = it.data?.result)
                }
            )
        }

    }

    companion object {
        val TAG = ProfileFragment::class.java.simpleName
    }
}