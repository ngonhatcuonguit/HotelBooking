package com.cuongngo.hotel_booking.ui.auth

import android.content.Intent
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.AppBaseActivityMVVM
import com.cuongngo.hotel_booking.base.view.DialogUtils
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.databinding.ActivityForgetPasswordBinding
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.local.AppPreferences
import com.cuongngo.hotel_booking.services.network.onResultReceived
import com.cuongngo.hotel_booking.utils.isValidateEmail
import com.cuongngo.hotel_booking.utils.validatePasswordLength

class ForgotPasswordActivity : AppBaseActivityMVVM<ActivityForgetPasswordBinding, UserViewModel>() {

    override val viewModel: UserViewModel by kodeinViewModel()

    override fun inflateLayout() = R.layout.activity_forget_password

    private var isValid = false

    override fun setUp() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.btnChange.setOnClickListener {
            viewModel.changePassword(
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString(),
                password_confirmation = binding.edtPasswordConfirm.text.toString()
            )
        }

        with(binding) {
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

            edtPasswordConfirm.addTextChangedListener {
                when {
                    (it.toString().isNullOrEmpty()) -> {
                        tvValidatePasswordConfirm.isVisible = true
                        tvValidatePasswordConfirm.text = "Vui lòng nhập mật khẩu"
                        isValid = false
                    }
                    (!validatePasswordLength(it.toString())) -> {
                        tvValidatePasswordConfirm.isVisible = true
                        tvValidatePasswordConfirm.text = "Mật khẩu từ 8 đến 32 kí tự"
                        isValid = false
                    }
                    !isPasswordEqual() -> {
                        tvValidatePasswordConfirm.isVisible = true
                        tvValidatePasswordConfirm.text = "Mật khẩu không trùng khớp"
                        isValid = false
                    }
                    else -> {
                        tvValidatePasswordConfirm.isVisible = false
                        isValid = true
                    }
                }

            }

        }
    }

    private fun isPasswordEqual() =
        binding.edtPassword.text.toString() == binding.edtPasswordConfirm.text.toString()

    override fun setUpObserver() {
        observeLiveDataChanged(viewModel.changePass) {
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    when (it.data?.result_code) {
                        1 -> {
                            AppPreferences.setNickName(it.data.data?.nickname.toString())
                            AppPreferences.setEmail(it.data.data?.email.toString())
                            showDialog(
                                title = "Chú ý",
                                message = "Bạn đã thay đổi mật khẩu thành công",
                                isCancelAble = false,
                                onDialogButtonClick = object : DialogUtils.DialogOnClickListener {
                                    override fun onClick(isPositiveClick: Boolean) {
                                        finish()
                                    }
                                })
                        }
                        300, 301, 302 -> {
                            showDialog(
                                title = "Chú ý",
                                message = it.data.result,
                                isCancelAble = false,
                                onDialogButtonClick = object : DialogUtils.DialogOnClickListener {
                                    override fun onClick(isPositiveClick: Boolean) {
                                        startActivity(
                                            Intent(
                                                this@ForgotPasswordActivity,
                                                LoginActivity::class.java
                                            )
                                        )
                                    }
                                })
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