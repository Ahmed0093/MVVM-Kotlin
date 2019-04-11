package com.development.task.mvvmproductapp.data.local

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductImage {
    @SerializedName("link")
    @Expose
    var link: String? = null
    @SerializedName("height")
    @Expose
    var height: String? = null
    @SerializedName("width")
    @Expose
    var width: String? = null

}
