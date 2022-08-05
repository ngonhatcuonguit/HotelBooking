package com.cuongngo.hotel_booking.ui.home

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cuongngo.hotel_booking.R
import com.cuongngo.hotel_booking.base.fragment.BaseFragment
import com.cuongngo.hotel_booking.databinding.FragmentHomeBinding
import com.cuongngo.hotel_booking.response.CategoryModel
import com.cuongngo.hotel_booking.ui.categories.CategoryAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>(), CategoryAdapter.SelectedListener {

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

    override fun inflateLayout() = R.layout.fragment_home

    override fun setUp() {
        setupRcvCategories()
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

    override fun onSelectedListener(categoryModel: CategoryModel) {
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

    override fun setUpObserver() { }

    companion object {
        val TAG = HomeFragment::class.java.simpleName
    }

}