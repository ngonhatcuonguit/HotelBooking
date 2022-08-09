package com.cuongngo.hotel_booking.ui.auth

import android.content.Intent
import android.graphics.Color
import android.text.SpannableStringBuilder
import androidx.core.text.color
import com.cuongngo.cinemax.utils.Constants.Key.Companion.ACTION
import com.cuongngo.cinemax.utils.Constants.Key.Companion.EDIT_PROFILE
import com.cuongngo.cinemax.utils.Constants.Key.Companion.SIGN_UP
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.BaseActivity
import com.cuongngo.hotel_booking.databinding.ActivityLoginBinding
import com.cuongngo.hotel_booking.ui.MainActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

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
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
            tvSignUp.setOnClickListener {
                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java).apply {
                    putExtra(ACTION, SIGN_UP)
                })
            }
        }
    }

    override fun setUpObserver() {

    }
}