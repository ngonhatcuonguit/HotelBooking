package com.cuongngo.hotel_booking.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.databinding.ItemHotelBinding
import com.cuongngo.hotel_booking.response.HotelModel

class HotelAdapter(
    listHotel : ArrayList<HotelModel>,
    private val selectedListener: SelectedListener
): RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    private var listHotel = listHotel

    class HotelViewHolder(
        val itemHotelBinding: ItemHotelBinding
    ): RecyclerView.ViewHolder(itemHotelBinding.root)

    interface SelectedListener {
        fun onHotelSelectedListener(hotelModel: HotelModel)
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        return HotelViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_hotel,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val binding = holder.itemHotelBinding
        val item = listHotel[position]
        binding.hotel = item

        binding.root.setOnClickListener {
            selectedListener.onHotelSelectedListener(item)
        }

    }

    fun submitListHotel(listHotel: List<HotelModel>){
        this.listHotel.addAll(listHotel)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return  listHotel.size
    }
}