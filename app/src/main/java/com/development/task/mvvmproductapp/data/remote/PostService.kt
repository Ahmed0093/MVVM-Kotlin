package com.development.task.mvvmproductapp.data.remote

import com.development.task.mvvmproductapp.data.local.Product
import io.reactivex.Single
import org.w3c.dom.Comment
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {
    @GET("https://limitless-forest-98976.herokuapp.com/")
    fun getPosts(): Single<List<Product>>


}