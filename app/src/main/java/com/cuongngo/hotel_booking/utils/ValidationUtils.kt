package com.cuongngo.hotel_booking.utils

import android.text.TextUtils
import android.util.Patterns
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import java.util.*

fun validateFullNameLength(name: String): Boolean {
    if(name.length<6 || name.length>64) return false
    return true
}

fun validatePasswordLength(name: String): Boolean {
    if(name.length<8 || name.length>32) return false
    return true
}

fun validateFullName(name: String): Boolean {
    return name.matches(Regex(FULLNAME_PATTERN))
}

fun CharSequence.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun isValidateEmail(email: String): Boolean {
    return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun validatePassWord(password: String): Boolean {
    password.let {
        val passwordPattern = "[^a-zA-Z0-9]"
        val passwordMatcher = Regex(passwordPattern)
        return passwordMatcher.find(password) != null
    }
}

fun validCellPhone(number: String): Boolean {
    if (number.length in 10..12) return true
    return false
}

fun String.isValidPhone(): Boolean {
    return isPhoneNumberValid(this, "VN")
}

fun isPhoneNumberValid(
    phoneNumber: String?,
    regionCode: String?
): Boolean {
    //NOTE: This should probably be a member variable.
    val phoneUtil: PhoneNumberUtil = PhoneNumberUtil.getInstance()
    try {
        val numberProto: Phonenumber.PhoneNumber = phoneUtil.parse(phoneNumber, regionCode?.toUpperCase(Locale.ROOT))
        return phoneUtil.isValidNumber(numberProto)
    } catch (e: Throwable) {
        System.err.println("NumberParseException was thrown: $e")
    }
    return false
}

private const val FULLNAME_PATTERN =
    "^[a-zA-ZÀÁÂÃÈÉÊẾÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ" +
            "ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ" +
            "ụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$"