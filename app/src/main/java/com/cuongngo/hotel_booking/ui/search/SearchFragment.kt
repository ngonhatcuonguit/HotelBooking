package com.cuongngo.hotel_booking.ui.search

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragment
import com.cuongngo.hotel_booking.base.fragment.BaseFragmentMVVM
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.databinding.FragmentSearchBinding
import com.cuongngo.hotel_booking.ext.observeLiveDataChanged
import com.cuongngo.hotel_booking.response.CategoryModel
import com.cuongngo.hotel_booking.response.HotelModel
import com.cuongngo.hotel_booking.services.network.onResultReceived
import com.cuongngo.hotel_booking.ui.categories.CategoryAdapter
import com.cuongngo.hotel_booking.ui.home.HomeViewModel
import com.cuongngo.hotel_booking.ui.home.HotelAdapter
import com.cuongngo.hotel_booking.ui.hoteldetail.HotelDetailActivity

class SearchFragment : BaseFragmentMVVM<FragmentSearchBinding,HomeViewModel>(), HotelAdapter.SelectedListener, CategoryAdapter.SelectedListener {

    override val viewModel: HomeViewModel by kodeinViewModel()

    private lateinit var hotelAdapter: HotelAdapter
    private lateinit var categoryAdapter: CategoryAdapter
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
        viewModel.getListHotel()
        setupRcvCategories()
    }

    private fun setupRcvHotel(listHotel: List<HotelModel>){
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

    override fun setUpObserver() {
        observeLiveDataChanged(viewModel.listHotel){
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    setupRcvHotel(it.data?.data?.hotels.orEmpty())
                },
                onError = {
                    hideProgressDialog()
                }
            )
        }

    }

    companion object {
        val TAG = SearchFragment::class.java.simpleName
    }

}