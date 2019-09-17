package com.development.task.mvvmproductapp.data.adsdata.remote

import com.development.task.mvvmproductapp.data.local.Products
import io.reactivex.Observable
import retrofit2.http.GET

interface AdsService {

    @GET("/")
    fun getProducts(): Observable<Products>
}