package com.cuongngo.hotel_booking.ui.search

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragment
import com.cuongngo.hotel_booking.databinding.FragmentSearchBinding
import com.cuongngo.hotel_booking.response.CategoryModel
import com.cuongngo.hotel_booking.response.HotelModel
import com.cuongngo.hotel_booking.ui.categories.CategoryAdapter
import com.cuongngo.hotel_booking.ui.home.HotelAdapter
import com.cuongngo.hotel_booking.ui.hoteldetail.HotelDetailActivity

class SearchFragment : BaseFragment<FragmentSearchBinding>(), HotelAdapter.SelectedListener, CategoryAdapter.SelectedListener {

    private lateinit var hotelAdapter: HotelAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    var listHotel = arrayListOf<HotelModel>()
    var categorySelected: CategoryModel? = null
    var listCategory = arrayOf(
        CategoryModel(
            1,
            "All Hotel",
            false
        ),
        CategoryModel(
            1,
            "Recommended",
            false
        ),
        CategoryModel(
            1,
            "Popular",
            false
        ),
        CategoryModel(
            1,
            "Trending",
            false
        )
    )

    override fun inflateLayout() = R.layout.fragment_search

    override fun setUp() {
        setupRcvCategories()
        setupRcvHotel()
    }

    private fun setupRcvHotel(){
        hotelAdapter = HotelAdapter(
            arrayListOf(),
            this
        )

        hotelAdapter.submitListHotel(listHotel.toList())
        binding.rcvListHotel.adapter = hotelAdapter

    }

    private fun setupRcvCategories(){
        val gridLayoutManager =
            GridLayoutManager(requireActivity(), 1, RecyclerView.HORIZONTAL, false)

        categoryAdapter = CategoryAdapter(
            arrayListOf(),
            this
        )
        if (categorySelected == null){
            listCategory.firstOrNull()?.is_selected = true
            categorySelected = listCategory.firstOrNull()
        }

        categoryAdapter.submitListCategory(listCategory.toList())
        binding.rvListFilter.adapter = categoryAdapter

    }

    override fun onHotelSelectedListener(hotelModel: HotelModel) {
        startActivity(Intent(context, HotelDetailActivity::class.java))
    }


    override fun onCategorySelectedListener(categoryModel: CategoryModel) {
        handleChooseCategory(categoryModel)
    }

    private fun handleChooseCategory(categoryModel: CategoryModel){
        categorySelected = categoryModel
        val oldData = listCategory.find { it.is_selected }
        val oldIndex = listCategory.indexOf(oldData)
        val index = listCategory.indexOf(categoryModel)

        oldData?.is_selected = false
        categoryModel.is_selected = true
        listCategory[oldIndex] = oldData ?: return
        listCategory[index] = categoryModel
        categoryAdapter.notifyItemChanged(index)
        categoryAdapter.notifyItemChanged(oldIndex)
    }

    override fun setUpObserver() {}

    companion object {
        val TAG = SearchFragment::class.java.simpleName
    }

}