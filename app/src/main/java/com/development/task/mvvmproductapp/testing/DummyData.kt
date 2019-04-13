package com.development.task.mvvmproductapp.testing

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.VisibleForTesting
import com.development.task.mvvmproductapp.data.local.ProductModel
import com.development.task.mvvmproductapp.data.local.PImage
import com.development.task.mvvmproductapp.data.local.Products

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
object DummyData {
    fun getDummy() : Products {
                val products: Products
        var data: List<ProductModel> = emptyList()
        val image: PImage = PImage("http://dummyimage.com/150x178.png/5fa2dd/ffffff", "150", "120")

        val itemdata = ProductModel(1,"title1", "description1", 6, image)
        val itemdata2 = ProductModel(2, "title2", "descropionnn2", 7,  image)

        data += itemdata

        data += itemdata2
        products = Products(data, 10)

      return products
    }
    fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }

    val liveData = MutableLiveData<String>().default("Initial value!")
}