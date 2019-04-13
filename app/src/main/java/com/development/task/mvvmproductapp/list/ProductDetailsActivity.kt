package com.development.task.mvvmproductapp.list

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.development.task.mvvmproductapp.R
import com.development.task.mvvmproductapp.constants.Constants
import com.development.task.mvvmproductapp.constants.Constants.SELECTED_POST
import com.development.task.mvvmproductapp.data.local.ProductModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.product_item.*
import kotlinx.android.synthetic.main.product_item.view.*
import javax.inject.Inject


class ProductDetailsActivity : AppCompatActivity() {

    private val TAG = "DetailsActivity"
    private var selectedPost: ProductModel? = null
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
        if (!intent.hasExtra(Constants.SELECTED_POST)) {
            Log.d(TAG, "getIntentData: could not find selected post")
            finish()
            return
        }

        selectedPost = intent.getParcelableExtra(SELECTED_POST)
        tvTitleDetails.text = selectedPost?.title
        tvPriceDetails.text = selectedPost?.price.toString() + "$"
        tvDescription.text = selectedPost?.body
        glideRequestManager
            .load(selectedPost?.image?.link)
            .placeholder(R.drawable.ic_mtrl_chip_checked_circle)
            .fitCenter()
            .into(ivAvatarDetails)


    }



}
