package com.cuongngo.hotel_booking.ui.auth

import android.content.Intent
import android.graphics.Color
import android.text.SpannableStringBuilder
import androidx.core.text.color
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.AppBaseActivityMVVM
import com.cuongngo.hotel_booking.base.view.DialogUtils
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.databinding.ActivityLoginBinding
import com.cuongngo.hotel_booking.ext.WTF
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.local.AppPreferences
import com.cuongngo.hotel_booking.services.network.onResultReceived
import com.cuongngo.hotel_booking.ui.MainActivity
import com.cuongngo.hotel_booking.utils.Constants.Key.Companion.ACTION
import com.cuongngo.hotel_booking.utils.Constants.Key.Companion.SIGN_UP
import com.cuongngo.hotel_booking.utils.isValidateEmail
import com.cuongngo.hotel_booking.utils.validatePasswordLength

class LoginActivity : AppBaseActivityMVVM<ActivityLoginBinding, UserViewModel>() {

    override val viewModel: UserViewModel by kodeinViewModel()

    override fun inflateLayout() = R.layout.activity_login

    var isValid = false

    override fun setUp() {

        val signUpTextBuilder = SpannableStringBuilder()
            .color(Color.parseColor("#000000")){ append("Don’t have an account?  ")}
            .color(Color.parseColor("#1AB65C")){ append("Sign up") }

        binding.tvSignUp.text = signUpTextBuilder

        with(binding){
            ivBack.setOnClickListener {
                onBackPressed()
            }
            btnSignIn.setOnClickListener {
                if (isValid){
                    viewModel.login(
                        email = edtEmail.text.toString(),
                        password = edtPassword.text.toString()
                    )
                }
            }
            tvSignUp.setOnClickListener {
                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java).apply {
                    putExtra(ACTION, SIGN_UP)
                })
            }

            edtPassword.addTextChangedListener {
                when {
                    (it.toString().isNullOrEmpty()) -> {
                        tvValidatePassword.isVisible = true
                        tvValidatePassword.text = "Vui lòng nhập mật khẩu"
                        isValid = false
                    }
                    (!validatePasswordLength(it.toString())) -> {
                        tvValidatePassword.isVisible = true
                        tvValidatePassword.text = "Mật khẩu từ 8 đến 32 kí tự"
                        isValid = false
                    }
                    else -> {
                        tvValidatePassword.isVisible = false
                        isValid = true
                    }
                }

            }

            edtEmail.addTextChangedListener {
                when {
                    !isValidateEmail(it.toString()) -> {
                        tvValidateEmail.isVisible = true
                        tvValidateEmail.text = "Email không đúng định dạng"
                        isValid = false
                    }

                    (it.toString().isNullOrEmpty()) -> {
                        tvValidateEmail.isVisible = true
                        tvValidateEmail.text = "Vui lòng nhập email"
                        isValid = false
                    }

                    else -> {
                        tvValidateEmail.isVisible = false
                        isValid = true
                    }
                }

            }

        }

    }

    override fun setUpObserver() {
        observeLiveDataChanged(viewModel.login){
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    when(it.data?.result_code){
                        1 -> {
                            AppPreferences.setUserAccessToken(it.data.data.access_token.toString())
                            AppPreferences.setNickName(it.data.data.user?.name.toString())
                            AppPreferences.setEmail(it.data.data.user?.email.toString())
                            AppPreferences.setPhone(it.data.data.user?.phone.toString())
                            finish()
                        }
                        else -> {
                            showDialog(title = "Chú ý", message = it.data?.result)
                        }
                    }
                },
                onError = {
                    hideProgressDialog()
                    showDialog(title = "Chú ý", message = it.data?.result)
                }
            )
        }
    }
}