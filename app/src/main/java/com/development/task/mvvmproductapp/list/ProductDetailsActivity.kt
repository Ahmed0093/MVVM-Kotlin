package com.development.task.mvvmproductapp.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import android.util.Log
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.development.task.mvvmproductapp.R
import com.development.task.mvvmproductapp.constants.Constants
import com.development.task.mvvmproductapp.constants.Constants.SELECTED_PRODUCT
import com.development.task.mvvmproductapp.data.local.ProductModel
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject


class ProductDetailsActivity : AppCompatActivity() {

    private val TAG = "DetailsActivity"
    private var selectedProductModel: ProductModel? = null
    private val component by lazy { ProductDH.listComponent() }
    @Inject
    lateinit var glideRequestManager: RequestManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        component.inject(this)
        getIntentData()
    }

    private fun getIntentData() {
        if (!intent.hasExtra(Constants.SELECTED_PRODUCT)) {
            Log.d(TAG, "getIntentData: could not find selected post")
            finish()
            return
        }

        selectedProductModel = intent.getParcelableExtra(SELECTED_PRODUCT)
        tvTitleDetails.text = selectedProductModel?.title
        tvPriceDetails.text = selectedProductModel?.price.toString()+"$"
        tvDescription.text = selectedProductModel?.body
        glideRequestManager
            .load(selectedProductModel?.image?.link)
            .placeholder(R.drawable.ic_mtrl_chip_checked_circle)
            .apply(RequestOptions().override(selectedProductModel?.image?.width?.toInt()!!, selectedProductModel?.image?.height?.toInt()!!))
            .into(ivAvatarDetails)


    }



}
