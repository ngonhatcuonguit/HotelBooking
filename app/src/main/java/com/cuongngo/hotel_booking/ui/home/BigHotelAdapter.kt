package com.cuongngo.hotel_booking.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cuongngo.hotel_booking.App
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.databinding.ItemBigHotelBinding
import com.cuongngo.hotel_booking.response.HotelModel

class BigHotelAdapter(
    listHotel: ArrayList<HotelModel>,
    private val selectedListener: SelectedListener
): RecyclerView.Adapter<BigHotelAdapter.BigHotelViewHolder>() {

    private var listHotel = listHotel

    interface SelectedListener {
        fun onBigHotelSelectedListener(hotelModel: HotelModel)
    }

    class BigHotelViewHolder(
        val itemBigHotelBinding: ItemBigHotelBinding
    ): RecyclerView.ViewHolder(itemBigHotelBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BigHotelViewHolder {
        return BigHotelViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_big_hotel,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BigHotelViewHolder, position: Int) {
        val binding = holder.itemBigHotelBinding
        val item = listHotel[position]
        binding.hotel = item

        val paddingStart = App.getResources().getDimensionPixelOffset(R.dimen._20dp)
        val paddingEnd = App.getResources().getDimensionPixelOffset(R.dimen._18dp)

        if (item == listHotel.firstOrNull()){
            binding.clContainer.setPadding(paddingStart,0,paddingEnd,0)
        }

        binding.root.setOnClickListener {
            selectedListener.onBigHotelSelectedListener(item)
        }

    }

    override fun getItemCount(): Int {
        return listHotel.size
    }

    fun submitListHotel(listHotel: List<HotelModel>){
        this.listHotel.addAll(listHotel)
        notifyDataSetChanged()
    }

}