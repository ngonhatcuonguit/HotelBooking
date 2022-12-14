package com.cuongngo.hotel_booking.base.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.cuongngo.hotel_booking.base.view.BaseView
import com.cuongngo.hotel_booking.base.view.ProgressDialog
import com.cuongngo.hotel_booking.R
import com.skydoves.transformationlayout.onTransformationStartContainer
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein


abstract class BaseActivity <DB : ViewDataBinding>: AppCompatActivity(), KodeinAware, BaseView {
    open lateinit var binding: DB
    override val kodein by kodein()

    private val progressDialog by lazy {
        ProgressDialog(this)
    }
    private var ignoreBackPressed = false
    val isIgnoreBackPressed get() = ignoreBackPressed

    @LayoutRes
    abstract fun inflateLayout(): Int

    override fun provideLoading() : ProgressDialog = progressDialog

    override fun provideContext(): Context? = this

    override fun provideRootView(): View? = binding.root

    open fun getStatusBarColor() = R.color.white

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, inflateLayout())
        hideSystemUI()
        setStatusBarColor(getStatusBarColor())
        setUp()
        setUpObserver()
    }

    open fun setDefaultStatusBarColor(){
        window.also {
            it.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            it.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            it.statusBarColor = getColor(R.color.white)
        }
    }

    open fun enableLightStatusBar(){
        val window = window
        val decorView: View = window.decorView
        WindowInsetsControllerCompat(window, decorView).also { wic ->
            wic.isAppearanceLightStatusBars = true // true or false as desired.
        }
        window.statusBarColor = customStatusBarColor

        /** DEPRECATED -> not use it, just for learning something old
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.WHITE
         **/
    }

    @ColorInt
    open val customStatusBarColor: Int = Color.TRANSPARENT

    protected fun setStatusBarColor(@DrawableRes id: Int) {
        window.statusBarColor = Color.TRANSPARENT
    }

    /**
     * If increase version code to 30 (R) , remove brackets
     * */
    private fun hideSystemUI(){
        /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
              window.setDecorFitsSystemWindows(false)
              window.insetsController?.let {
                  it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                  it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
              }
          } else {*/
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                )
        /*}*/
    }

    fun isShowKeyBoard() : Boolean {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.isAcceptingText
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val view: View? = currentFocus
        val ret = super.dispatchTouchEvent(event)
        if (view is EditText) {
            currentFocus?.let {
                val w: View = it
                val scrcoords = IntArray(2)
                w.getLocationOnScreen(scrcoords)
                val x: Float = event.rawX + w.left - scrcoords[0]
                val y: Float = event.rawY + w.top - scrcoords[1]
                if (event.action == MotionEvent.ACTION_UP
                    && (x < w.left || x >= w.right || y < w.top || y > w.bottom)
                ) {
                    hideKeyboard()

                }
            }
        }
        return ret
    }

}