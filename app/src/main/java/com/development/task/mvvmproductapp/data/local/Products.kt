package com.development.task.mvvmproductapp.data.local

import com.google.gson.annotations.SerializedName

data class Products (@SerializedName("data") val data: List<ProductModel>,
                     @SerializedName("count") val count: Int)


