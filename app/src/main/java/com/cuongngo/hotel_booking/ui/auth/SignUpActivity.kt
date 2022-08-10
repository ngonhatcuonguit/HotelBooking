package com.cuongngo.hotel_booking.ui.auth

import android.util.Log
import com.cuongngo.hotel_booking.utils.Constants.Key.Companion.ACTION
import com.cuongngo.hotel_booking.utils.Constants.Key.Companion.EDIT_PROFILE
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.AppBaseActivityMVVM
import com.cuongngo.hotel_booking.base.activity.BaseActivity
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.databinding.ActivitySignUpBinding
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.services.network.onResultReceived
import com.cuongngo.hotel_booking.utils.Constants.Key.Companion.SIGN_UP
import com.cuongngo.hotel_booking.utils.validateFullName
import com.cuongngo.hotel_booking.utils.validateFullNameLength

class SignUpActivity : AppBaseActivityMVVM<ActivitySignUpBinding, SignUpViewModel>() {

    override val viewModel: SignUpViewModel by kodeinViewModel()

    private val action by lazy { intent.getStringExtra(ACTION) ?:"" }

    override fun inflateLayout() = R.layout.activity_sign_up

    override fun setUp() {
        with(binding){
            ivBack.setOnClickListener {
                onBackPressed()
            }

            btnSignUp.setOnClickListener {
                if (action == SIGN_UP){
                    viewModel.signUp(
                        name = edtFullName.text.toString(),
                        email = edtEmail.text.toString(),
                        password = edtPassword.text.toString(),
                        password_confirmation = edtPasswordConfirm.text.toString(),
                        nickname = edtNickname.text.toString(),
                        phone = edtPhoneNumber.text.toString(),
                        birthday = "10-01-1995",
                        gender = "1"
                    )
                }
            }

        }

        if (action == EDIT_PROFILE){
            binding.tvTitle.text = "Edit Profile"
            binding.btnSignUp.text ="Update"
        }

//        if (!validateFullNameLength(binding.edtFullName.text.toString())) {
//            showDialog(
//                title = "Chú ý",
//                message = "Tên từ 6 đến 64 kí tự"
//            )
//        } else if (!validateFullName(binding.edtFullName.text.toString())) {
//            showDialog(
//                title = "Chú ý",
//                message = "Tên không được chứa kí tự đặc biệt"
//            )
//        }

    }

    override fun setUpObserver() {
        observeLiveDataChanged(viewModel.signUp){
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    Log.d("test response", "${it.data?.data}")
                },
                onError = {

                }
            )
        }
    }

    companion object {
        val TAG = SignUpActivity::class.java.simpleName
    }

}