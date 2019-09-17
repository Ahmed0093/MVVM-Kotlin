package com.development.task.mvvmproductapp.list.model

import com.development.task.mvvmproductapp.data.adsdata.AdsList
import com.development.task.mvvmproductapp.data.adsdata.AdsModel
import com.development.task.mvvmproductapp.data.remote.ProductService
import io.reactivex.Observable

class ListRemoteData(private val productService: ProductService) : ListDataContract.Remote {
    override fun getAds(): Observable<List<AdsModel>> = productService.getAdsApi()

    override fun getProducts() = productService.getProducts()
}