package com.cuongngo.hotel_booking.ui.categories

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cuongngo.hotel_booking.App
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.databinding.ItemCategoryBinding
import com.cuongngo.hotel_booking.response.CategoryModel

class CategoryAdapter(
    listCategory: ArrayList<CategoryModel>,
    private val selectedListener: SelectedListener
): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val listCategory = listCategory

    interface SelectedListener{
        fun onSelectedListener(categoryModel: CategoryModel)
    }

    class CategoryViewHolder(
        val itemCategoryBinding: ItemCategoryBinding
    ): RecyclerView.ViewHolder(itemCategoryBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_category,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val binding = holder.itemCategoryBinding
        val item = listCategory[position]
        val paddingStart = App.getResources().getDimensionPixelOffset(R.dimen._20dp)
        val paddingEnd = App.getResources().getDimensionPixelOffset(R.dimen._8dp)

        binding.category = item
        if (item == listCategory.firstOrNull()){
            binding.clContainer.setPadding(paddingStart,0,paddingEnd,0)
        }else binding.clContainer.setPadding(0,0,paddingEnd,0)

        binding.root.setOnClickListener {
            selectedListener.onSelectedListener(item)
        }

        if (item.is_selected){
            binding.tvCategory.setBackgroundColor(Color.parseColor("#1AB65C"))
            binding.tvCategory.setTextColor(Color.parseColor("#FFFFFF"))
        }else {
            binding.tvCategory.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.tvCategory.setTextColor(Color.parseColor("#1AB65C"))
        }

    }

    override fun getItemCount(): Int {
        return  listCategory.size
    }

    fun submitListCategory(listCategory: List<CategoryModel>){
        this.listCategory.addAll(listCategory)
        notifyDataSetChanged()
    }

}