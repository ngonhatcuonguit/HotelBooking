package com.cuongngo.hotel_booking.base.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.cuongngo.hotel_booking.base.view.BaseView
import com.cuongngo.hotel_booking.utils.convertDpToPixel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

abstract class BaseDialog<DB: ViewDataBinding> : DialogFragment(),KodeinAware, BaseView {
    open lateinit var binding: DB

    override val kodein by kodein()

    @LayoutRes
    abstract fun inflateLayout(): Int

    override fun provideContext(): Context? = requireContext()

    override fun provideRootView(): View? = view

    override fun onStart() {
        super.onStart()
        if (showMatchParent()){
            val dialog = dialog
            if (dialog != null) {
                val width = ViewGroup.LayoutParams.MATCH_PARENT
                val height = ViewGroup.LayoutParams.MATCH_PARENT
                dialog.window?.setLayout(width, height)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,inflateLayout(),container,false)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, convertDpToPixel(20f,requireContext()).toInt())
        dialog?.window?.also {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.requestFeature(Window.FEATURE_NO_TITLE)
            it.setBackgroundDrawable(inset)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        setUpObserver()
    }

    open fun show(){
        this.show(activity?.supportFragmentManager?:return, this::class.java.simpleName)
    }

    open fun showMatchParent() = false
}