package com.cuongngo.hotel_booking.ui.mybooking

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