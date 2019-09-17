package com.development.task.mvvmproductapp.data.remote

import com.development.task.mvvmproductapp.data.adsdata.AdsList
import com.development.task.mvvmproductapp.data.adsdata.AdsModel
import com.development.task.mvvmproductapp.data.local.Products
import io.reactivex.Observable
import retrofit2.http.GET

interface ProductService {
    @GET("/")
    fun getProducts(): Observable<Products>

    @GET("/get_ad/?solo=false")
    fun getAdsApi(): Observable<List<AdsModel>>
}