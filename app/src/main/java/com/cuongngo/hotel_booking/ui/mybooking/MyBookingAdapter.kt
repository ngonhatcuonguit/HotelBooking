package com.cuongngo.hotel_booking.ui.mybooking

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.databinding.ItemMyBookingBinding
import com.cuongngo.hotel_booking.response.BookingModel

class MyBookingAdapter(
    listMyBooking: ArrayList<BookingModel>,
    private var listener: Listener
) : RecyclerView.Adapter<MyBookingAdapter.MyBookingViewHolder>() {

    var listMyBooking = listMyBooking

    class MyBookingViewHolder(
        val itemMyBookingBinding: ItemMyBookingBinding
    ): RecyclerView.ViewHolder(itemMyBookingBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBookingViewHolder {
        return MyBookingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_my_booking,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyBookingViewHolder, position: Int) {
        val item = listMyBooking[position]
        val binding = holder.itemMyBookingBinding
        binding.booking = item

        binding.tvCancelBooking.setOnClickListener {
            listener.onCancel(item.id ?:return@setOnClickListener)
        }

        binding.tvViewTicket.setOnClickListener {
            listener.onViewTicket(item.id ?: return@setOnClickListener)
        }

        when(item.status){
            2, 3 -> {
                binding.tvCancelBooking.isClickable = false
                binding.tvCancelBooking.setBackgroundColor(Color.parseColor("#C4C4C4"))
                binding.tvCancelBooking.setTextColor(Color.parseColor("#FFFFFF"))
            }
            else -> {
                binding.tvCancelBooking.isClickable = true
                binding.tvCancelBooking.setBackgroundColor(Color.parseColor("#FFFFFF"))
                binding.tvCancelBooking.setTextColor(Color.parseColor("#1AB65C"))
            }
        }

    }

    override fun getItemCount(): Int {
        return listMyBooking.size
    }

    interface Listener {
        fun onCancel(booking_id: Int)
        fun onViewTicket(booking_id: Int)
    }

    fun submitListBooking(listMyBooking: List<BookingModel>) {
        this.listMyBooking.addAll(listMyBooking)
        notifyDataSetChanged()
    }

}