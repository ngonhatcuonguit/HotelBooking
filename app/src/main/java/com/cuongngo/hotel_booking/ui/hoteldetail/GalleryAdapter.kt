package com.cuongngo.hotel_booking.ui.hoteldetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cuongngo.hotel_booking.App
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.databinding.ItemGalleryBinding
import com.cuongngo.hotel_booking.response.GalleryModel
import com.cuongngo.hotel_booking.response.Image

class GalleryAdapter(
    listGallery: ArrayList<Image>
): RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    private var listGallery = listGallery

    class GalleryViewHolder(
        val itemGalleryBinding: ItemGalleryBinding
    ): RecyclerView.ViewHolder(itemGalleryBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_gallery,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val binding = holder.itemGalleryBinding
        val item = listGallery[position]

        binding.imageHotel = item

        val paddingStart = App.getResources().getDimensionPixelOffset(R.dimen._20dp)
        val paddingEnd = App.getResources().getDimensionPixelOffset(R.dimen._10dp)

        if (item == listGallery.firstOrNull()){
            binding.clContainer.setPadding(paddingStart,0,paddingEnd,0)
        }
    }

    fun submitListGallery(listGallery: List<Image> ){
        this.listGallery.addAll(listGallery)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listGallery.size
    }
}