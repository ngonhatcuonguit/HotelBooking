package com.cuongngo.hotel_booking.ui.auth

import android.content.Intent
import android.graphics.Color
import android.text.SpannableStringBuilder
import androidx.core.text.color
import com.cuongngo.hotel_booking.utils.Constants.Key.Companion.ACTION
import com.cuongngo.hotel_booking.utils.Constants.Key.Companion.SIGN_UP
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.AppBaseActivityMVVM
import com.cuongngo.hotel_booking.base.activity.BaseActivity
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.databinding.ActivityLoginBinding
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.local.AppPreferences
import com.cuongngo.hotel_booking.services.network.onResultReceived
import com.cuongngo.hotel_booking.ui.MainActivity

class LoginActivity : AppBaseActivityMVVM<ActivityLoginBinding, UserViewModel>() {

    override val viewModel: UserViewModel by kodeinViewModel()

    override fun inflateLayout() = R.layout.activity_login

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
                viewModel.login(
                    email = edtEmail.text.toString(),
                    password = edtPassword.text.toString()
                )
            }
            tvSignUp.setOnClickListener {
                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java).apply {
                    putExtra(ACTION, SIGN_UP)
                })
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
                    AppPreferences.setUserAccessToken(it.data?.data?.access_token.toString())
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                },
                onError = {
                    showDialog(title = "Chú ý", message = it.data?.result)
                }
            )
        }
    }
}