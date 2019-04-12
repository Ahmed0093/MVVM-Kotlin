package com.development.task.mvvmproductapp.data.remote

import com.development.task.mvvmproductapp.data.local.Products
import io.reactivex.Observable
import retrofit2.http.GET

interface ProductService {
    @GET("/")
    fun getProducts(): Observable<Products>


}