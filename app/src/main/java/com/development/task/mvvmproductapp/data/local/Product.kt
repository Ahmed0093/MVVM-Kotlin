package com.development.task.mvvmproductapp.data.local

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.media.Image
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Class which provides a model for Product
 * @constructor Sets all properties of the post
 * @property productId the unique identifier of the productId
 * @property id the unique identifier of the product in DB
 * @property title the title of the Product
 * @property body the content of the product
 */
@Entity
data class Product(
    @field:PrimaryKey
    var id: Int,
    @SerializedName("id")
    @Expose
    var productId: Int,
    @SerializedName("name")
    @Expose
    var title: String,
    @SerializedName("productDescription")
    @Expose
    var body: String,
    @SerializedName("price")
    @Expose
    var price: Int,
    @SerializedName("image")
    @Expose
    @Embedded var image: ProductImage

)