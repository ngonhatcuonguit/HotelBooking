package com.cuongngo.hotel_booking.ui.search

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragment
import com.cuongngo.hotel_booking.base.fragment.BaseFragmentMVVM
import com.cuongngo.hotel_booking.base.viewmodel.kodeinViewModel
import com.cuongngo.hotel_booking.common.collection.EndlessRecyclerViewScrollListener
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
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    var categorySelected: CategoryModel? = null
    var listCategory = arrayOf(
        CategoryModel(
            1,
            "All Hotel",
            "empty ",
            false
        ),
        CategoryModel(
            1,
            "Recommended",
            "is_recommand",
            false
        ),
        CategoryModel(
            1,
            "Popular",
            "is_popular ",
            false
        ),
        CategoryModel(
            1,
            "Trending",
            "is_trending",
            false
        )
    )

    override fun inflateLayout() = R.layout.fragment_search

    override fun setUp() {
        viewModel.getListHotel()
        setupRcvCategories()
        val layoutManager = LinearLayoutManager(requireContext())
        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.loadMoreListHotel()
            }
        }
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
        if (categoryModel.param != viewModel.filtering){
            viewModel.filtering = categoryModel.param
            viewModel.getListHotel()
        }else {
            viewModel.filtering = categoryModel.param
        }
    }

    override fun setUpObserver() {

        observeLiveDataChanged(viewModel.categoryFilter){
        }

        observeLiveDataChanged(viewModel.listHotel){
            it.onResultReceived(
                onLoading = {
                    showProgressDialog()
                },
                onSuccess = {
                    hideProgressDialog()
                    if (it.data?.result_code == 1){
                        if (!it.data?.cursors?.after.isNullOrEmpty()){
                            viewModel.after = it.data?.cursors?.after.orEmpty()
                        }
                        setupRcvHotel(it.data?.data?.hotels.orEmpty())
                    }else {
                        showDialog("Warning", it.data?.result)
                    }
                },
                onError = {
                    hideProgressDialog()
                    showDialog("Warning", it.data?.result)
                }
            )
        }

    }

    companion object {
        val TAG = SearchFragment::class.java.simpleName
    }

}