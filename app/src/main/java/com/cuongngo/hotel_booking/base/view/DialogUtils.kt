package com.cuongngo.hotel_booking.base.view

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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

        val tvMessage = alertDialog.findViewById<TextView>(R.id.tv_message_base)
        val tvTitle = alertDialog.findViewById<TextView>(R.id.tv_title_base)
        val btnConfirm = alertDialog.findViewById<TextView>(R.id.btn_confirm_base)
        val btnNegative = alertDialog.findViewById<TextView>(R.id.btn_negative_base)

        message?.let {
            tvMessage?.text =
                HtmlCompat.fromHtml(message, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }

        if (title == null) {
            tvTitle?.visibility = View.GONE
        } else {
            tvTitle?.visibility = View.VISIBLE
            tvTitle?.text = title
        }

        if (positiveText == null) {
            //default positive text
            btnConfirm?.text = "Xác nhận"
        } else {
            btnConfirm?.text = positiveText
        }

        if (negativeText == null) {
            btnNegative?.visibility = View.GONE
        } else {
            btnNegative?.text = negativeText
        }

        alertDialog.setCancelable(isCancelAble)
        onDismissListener?.let {
            alertDialog.setOnDismissListener(it)
        }

        btnConfirm?.setOnClickListener {
            alertDialog.dismiss()
            onDialogButtonClick?.onClick(true)
        }

        btnNegative?.setOnClickListener {
            alertDialog.dismiss()
            onDialogButtonClick?.onClick(false)
        }

        tvMessage?.setOnClickListener {
            alertDialog.dismiss()
            onMessageListener?.onClick()
        }

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