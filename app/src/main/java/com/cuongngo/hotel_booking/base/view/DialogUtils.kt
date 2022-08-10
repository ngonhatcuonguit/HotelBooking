package com.cuongngo.hotel_booking.base.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.core.text.HtmlCompat
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.utils.convertDpToPixel

object DialogUtils {

    fun showAlertDialog(
        context: Context,
        title: String? = null,
        message: String? = null,
        positiveText: String? = null,
        negativeText: String? = null,
        isCancelAble: Boolean = false,
        onDismissListener: DialogInterface.OnDismissListener? = null,
        onDialogButtonClick: DialogOnClickListener? = null,
        onMessageListener: MessageListener? = null
    ): AlertDialog {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_base, null)
        val builder = AlertDialog.Builder(context, R.style.CustomAlertDialog)
            .setView(dialogView)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, convertDpToPixel(20f, context).toInt())
        val alertDialog = builder.show()

        alertDialog.window?.setBackgroundDrawable(inset)

//        message?.let {
//              alertDialog.tv_message_base.text =
//                HtmlCompat.fromHtml(message, HtmlCompat.FROM_HTML_MODE_LEGACY)
//        }
//
//        if (title == null) {
//            alertDialog.tv_title_base.visibility = View.GONE
//        } else {
//            alertDialog.tv_title_base.visibility = View.VISIBLE
//            alertDialog.tv_title_base.text = title
//        }
//
//        if (positiveText == null) {
//            //default positive text
//            alertDialog.btn_confirm_base.text = "Xác nhận"
//        } else {
//            alertDialog.btn_confirm_base.text = positiveText
//        }
//
//        if (negativeText == null) {
//            alertDialog.btn_negative_base.visibility = View.GONE
//        } else {
//            alertDialog.btn_negative_base.text = negativeText
//        }
//
//        alertDialog.setCancelable(isCancelAble)
//        onDismissListener?.let {
//            alertDialog.setOnDismissListener(it)
//        }
//
//        alertDialog.btn_confirm_base.setOnClickListener {
//            alertDialog.dismiss()
//            onDialogButtonClick?.onClick(true)
//        }
//
//        alertDialog.btn_negative_base.setOnClickListener {
//            alertDialog.dismiss()
//            onDialogButtonClick?.onClick(false)
//        }
//
//        alertDialog.tv_message_base.setOnClickListener{
//            alertDialog.dismiss()
//            onMessageListener?.onClick()
//        }

        return alertDialog
    }

    /**
     * isPositiveClick : true if positive button is clicked ,false otherwise
     */
    interface DialogOnClickListener {
        fun onClick(isPositiveClick: Boolean)
    }

    interface MessageListener{
        fun onClick()
    }
}