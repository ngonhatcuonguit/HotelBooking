package com.cuongngo.hotel_booking.ui.auth

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.activity.AppBaseActivityMVVM
import com.cuongngo.hotel_booking.base.view.DialogUtils
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.databinding.ActivitySignUpBinding
import com.cuongngo.hotel_booking.ext.WTF
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.local.AppPreferences
import com.cuongngo.hotel_booking.services.network.onResultReceived
import com.cuongngo.hotel_booking.utils.*
import com.cuongngo.hotel_booking.utils.Constants.Key.Companion.ACTION
import com.cuongngo.hotel_booking.utils.Constants.Key.Companion.EDIT_PROFILE
import com.cuongngo.hotel_booking.utils.Constants.Key.Companion.SIGN_UP
import java.util.*

class SignUpActivity : AppBaseActivityMVVM<ActivitySignUpBinding, UserViewModel>() {

    override val viewModel: UserViewModel by kodeinViewModel()

    private val action by lazy { intent.getStringExtra(ACTION) ?: "" }

    override fun inflateLayout() = R.layout.activity_sign_up

    private var isValid = false

    private var birthDay : String? = null
    private var gender : String? = null

    override fun setUp() {
        with(binding) {
            ivBack.setOnClickListener {
                onBackPressed()
            }

            if (action != SIGN_UP){
                clPassword.visibility = View.GONE
                clPasswordConfirm.visibility = View.GONE
                viewModel.getUser()
            }

            clDateOfBirth.setOnClickListener {
                showDatePicker()
            }

            btnSignUp.setOnClickListener {
                if (!isValid && birthDay.isNullOrEmpty() && gender.isNullOrEmpty()){

                }else {
                    if (action == SIGN_UP) {
                        viewModel.signUp(
                            name = edtFullName.text.toString(),
                            email = edtEmail.text.toString(),
                            password = edtPassword.text.toString(),
                            password_confirmation = edtPasswordConfirm.text.toString(),
                            nickname = edtNickname.text.toString(),
                            phone = edtPhoneNumber.text.toString(),
                            birthday = birthDay.toString(),
                            gender = gender.toString()
                        )
                    }else {
                        viewModel.changeInfo(
                            name = edtFullName.text.toString(),
                            email = edtEmail.text.toString(),
                            nickname = edtNickname.text.toString(),
                            phone = edtPhoneNumber.text.toString(),
                            birthday = birthDay.toString(),
                            gender = gender.toString()
                        )
                    }
                }

            }

            clGender.setOnClickListener {
                showPopupMenu(it)
            }

            edtPassword.addTextChangedListener {
                when {
                    (it.toString().isNullOrEmpty()) -> {
                        tvValidatePassword.isVisible = true
                        tvValidatePassword.text = "Vui l??ng nh???p m???t kh???u"
                        isValid = false
                    }
                    (!validatePasswordLength(it.toString())) -> {
                        tvValidatePassword.isVisible = true
                        tvValidatePassword.text = "M???t kh???u t??? 8 ?????n 32 k?? t???"
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
                        tvValidatePasswordConfirm.text = "Vui l??ng nh???p m???t kh???u"
                        isValid = false
                    }
                    (!validatePasswordLength(it.toString())) -> {
                        tvValidatePasswordConfirm.isVisible = true
                        tvValidatePasswordConfirm.text = "M???t kh???u t??? 8 ?????n 32 k?? t???"
                        isValid = false
                    }
                    !isPasswordEqual() -> {
                        tvValidatePasswordConfirm.isVisible = true
                        tvValidatePasswordConfirm.text = "M???t kh???u kh??ng tr??ng kh???p"
                        isValid = false
                    }
                    else -> {
                        tvValidatePasswordConfirm.isVisible = false
                        isValid = true
                    }
                }

            }

            edtEmail.addTextChangedListener {
                when {
                    !isValidateEmail(it.toString()) -> {
                        tvValidateEmail.isVisible = true
                        tvValidateEmail.text = "Email kh??ng ????ng ?????nh d???ng"
                        isValid = false
                    }

                    (it.toString().isNullOrEmpty()) -> {
                        tvValidateEmail.isVisible = true
                        tvValidateEmail.text = "Vui l??ng nh???p email"
                        isValid = false
                    }

                    else -> {
                        tvValidateEmail.isVisible = false
                        isValid = true
                    }
                }

            }

            edtFullName.addTextChangedListener {
                when {
                    (!validateFullNameLength(it.toString())) -> {
                        tvValidateFullName.isVisible = true
                        tvValidateFullName.text = "T??n t??? 6 ?????n 64 k?? t???"
                        isValid = false
                    }
                    (it.toString().isNullOrEmpty()) -> {
                        tvValidateFullName.isVisible = true
                        tvValidateFullName.text = "Vui l??ng nh???p h??? t??n"
                        isValid = false
                    }

                    else -> {
                        tvValidateFullName.isVisible = false
                        isValid = true
                    }

                }
            }

            edtNickname.addTextChangedListener {
                when {
                    (!validateFullNameLength(it.toString())) -> {
                        tvValidateNickname.isVisible = true
                        tvValidateNickname.text = "Nick name t??? 6 ?????n 64 k?? t???"
                        isValid = false
                    }
                    (it.toString().isNullOrEmpty()) -> {
                        tvValidateNickname.isVisible = true
                        tvValidateNickname.text = "Vui l??ng nh???p h??? nick name"
                        isValid = false
                    }

                    else -> {
                        tvValidateNickname.isVisible = false
                        isValid = true
                    }

                }
            }

            edtPhoneNumber.addTextChangedListener {
                when {
                    !it.toString().isValidPhone() -> {
                        tvValidatePhoneNumber.isVisible = true
                        tvValidatePhoneNumber.text = "?????nh d???ng s??? ??i???n tho???i kh??ng ????ng"
                        isValid = false
                    }
                    it.isNullOrEmpty() -> {
                        tvValidatePhoneNumber.isVisible = true
                        tvValidatePhoneNumber.text = "Vui l??ng nh???p s??? ??i???n tho???i"
                        isValid = false
                    }
                    else -> {
                        tvValidatePhoneNumber.isVisible = false
                        isValid = false
                    }
                }
            }

        }

            if (action == EDIT_PROFILE) {
                binding.tvTitle.text = "Edit Profile"
                binding.btnSignUp.text = "Update"
            }

    }

    override fun setUpObserver() {

        observeLiveDataChanged(viewModel.getUser){
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    when(it.data?.result_code){
                        1 -> {
                            with(binding){
                                edtEmail.setText("${it.data.data?.email}")
                                edtFullName.setText("${it.data.data?.name}")
                                edtNickname.setText("${it.data.data?.nickname}")
                                edtPhoneNumber.setText("${it.data.data?.phone}")
                                tvDateOfBirth.text = it.data.data?.birthday
                                when(it.data.data?.gender){
                                    1 -> {
                                        tvGender.text = "Nam"
                                    }
                                    2 -> {
                                        tvGender.text = "N???"
                                    }
                                    3 -> {
                                        tvGender.text = "Kh??c"
                                    }
                                }
                            }
                        }
                        300,301,302 -> {
                            showDialog(
                                title = "Ch?? ??",
                                message = it.data.result,
                                isCancelAble = false,
                                onDialogButtonClick = object : DialogUtils.DialogOnClickListener {
                                    override fun onClick(isPositiveClick: Boolean) {
                                        startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                                    }
                                } )
                        }
                        else -> {
                            showDialog(title = "Ch?? ??", message = it.data?.result)
                        }
                    }
                },
                onError = {
                    hideProgressDialog()
                    showDialog(title ="Ch?? ??", message = it.data?.result)
                }
            )
        }

        observeLiveDataChanged(viewModel.signUp){
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    when(it.data?.result_code){
                        1 -> {
                            showDialog(
                                title = "????ng k?? th??nh c??ng",
                                message = "B???n ???? ????ng k?? t??i kho???n th??nh c??ng, h??y ????ng nh???p ????? tr???i nghi???m Bolu ngay!",
                                isCancelAble = false,
                                onDialogButtonClick = object : DialogUtils.DialogOnClickListener {
                                    override fun onClick(isPositiveClick: Boolean) {
                                        finish()
                                    }
                                } )
                        }
                        300,301,302 -> {

                            showDialog(
                                title = "Ch?? ??",
                                message = it.data.result,
                                isCancelAble = false,
                                onDialogButtonClick = object : DialogUtils.DialogOnClickListener {
                                    override fun onClick(isPositiveClick: Boolean) {
                                        startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                                    }
                                } )
                        }
                        else -> {
                            showDialog(title = "Ch?? ??", message = it.data?.result)
                        }
                    }
                },
                onError = {
                    hideProgressDialog()
                    showDialog(title ="Ch?? ??", message = it.data?.result)
                }
            )
        }
        observeLiveDataChanged(viewModel.changeInfo){
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()

                    when(it.data?.result_code){
                        1 -> {
                            AppPreferences.setNickName(it.data.data?.nickname.toString())
                            AppPreferences.setEmail(it.data.data?.email.toString())
                            showDialog(
                                title = "Ch?? ??",
                                message = "B???n ???? thay ?????i th??ng tin th??nh c??ng",
                                isCancelAble = false,
                                onDialogButtonClick = object : DialogUtils.DialogOnClickListener {
                                    override fun onClick(isPositiveClick: Boolean) {
                                        finish()
                                    }
                                } )
                        }
                        300,301,302 -> {

                            showDialog(
                                title = "Ch?? ??",
                                message = it.data.result,
                                isCancelAble = false,
                                onDialogButtonClick = object : DialogUtils.DialogOnClickListener {
                                    override fun onClick(isPositiveClick: Boolean) {
                                        startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                                    }
                                } )
                        }
                        else -> {
                            showDialog(title = "Ch?? ??", message = it.data?.result)
                        }
                    }
                },
                onError = {
                    hideProgressDialog()
                    showDialog(title ="Ch?? ??", message = it.data?.result)
                }
            )
        }
    }


    private fun showDatePicker(){
        val calendar = Calendar.getInstance()
        val y = calendar.get(Calendar.YEAR)
        val m = calendar.get(Calendar.MONTH)
        val d = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                val dayStr = if (dayOfMonth < 10) "0${dayOfMonth}" else "$dayOfMonth"
                val mon = monthOfYear + 1
                val monthStr = if (mon < 10) "0${mon}" else "$mon"

                val output = "$year-$monthStr-$dayStr"
                binding.tvDateOfBirth.text = output
                birthDay = output
            },
            y,
            m,
            d
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    /**
     * Show the sort popup.
     * @param view where the popup will be anchored
     */
    @SuppressLint("InflateParams")
    private fun showPopupMenu(view: View) {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val root = inflater.inflate(R.layout.layout_type_gender, null)

        val clMale = root.findViewById(R.id.layout_male) as ConstraintLayout
        val clFeMale = root.findViewById(R.id.layout_female) as ConstraintLayout
        val clOther = root.findViewById(R.id.layout_other) as ConstraintLayout
        val genderPopupWindow = PopupWindow(
            root,
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            true
        )
        gender.let {
            when (it) {
                "1" -> {
                    val tvTitle = root.findViewById(R.id.tv_male) as TextView
                    val ivChecked = root.findViewById(R.id.cb_male) as ImageView
                    tvTitle.setTypeface(tvTitle.typeface, Typeface.BOLD)
                    ivChecked.isVisible = true
                }
                "2" -> {
                    val tvTitle = root.findViewById(R.id.tv_female) as TextView
                    val ivChecked = root.findViewById(R.id.cb_female) as ImageView
                    tvTitle.setTypeface(tvTitle.typeface, Typeface.BOLD)
                    ivChecked.isVisible = true
                }
                "3" -> {
                    val tvTitle = root.findViewById(R.id.tv_other) as TextView
                    val ivChecked = root.findViewById(R.id.cb_other) as ImageView
                    tvTitle.setTypeface(tvTitle.typeface, Typeface.BOLD)
                    ivChecked.isVisible = true
                }
            }
        }
        clMale.setOnClickListener {
            if (gender != "1") {
                onChooseGender("1")
            }
            genderPopupWindow.dismiss()
        }
        clFeMale.setOnClickListener {
            if (gender != "2") {
                onChooseGender("2")
            }
            genderPopupWindow.dismiss()
        }
        clOther.setOnClickListener {
            if (gender != "3") {
                onChooseGender("3")
            }
            genderPopupWindow.dismiss()
        }

        genderPopupWindow.elevation = convertDpToPixel(2F, this)

        //show popup menu
        val values = IntArray(2)
        view.getLocationInWindow(values)
        val positionOfIcon = values[1]
        val height = getScreenHeight() * 2 / 3
        if (positionOfIcon > height) {
            // when parent view in the bottom of the screen show popup up
            val offsetY = resources.getDimensionPixelSize(R.dimen._100dp)
            genderPopupWindow.showAsDropDown(view, 0, -offsetY, Gravity.BOTTOM)
        } else {
            // when parent view in the bottom of the screen show popup down
            genderPopupWindow.showAsDropDown(
                view,
                0,
                convertDpToPixel(8F, this).toInt(),
                Gravity.END
            )
        }
    }

    private fun onChooseGender(gender: String){
        this.gender = gender
        when(gender){
            "1" -> binding.tvGender.text = "Nam"
            "2" -> binding.tvGender.text = "N???"
            "3" -> binding.tvGender.text = "Kh??c"
        }
        refreshData()
    }

    private fun refreshData() {

    }

    private fun isPasswordEqual() =
        binding.edtPassword.text.toString() == binding.edtPasswordConfirm.text.toString()

    companion object {
        val TAG = SignUpActivity::class.java.simpleName
    }

}