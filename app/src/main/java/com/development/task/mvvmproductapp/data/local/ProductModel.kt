//package com.development.task.mvvmproductapp.data.local
//
//import com.google.gson.annotations.Expose
//import com.google.gson.annotations.SerializedName
//
//data class ProductModel (
//    @SerializedName("id") val id: Int,
//    @SerializedName("name") val name: String,
//    @SerializedName("productDescription") val productDescription: String,
//    @SerializedName("image") val image: PImage,
//    @SerializedName("price") val price: Int)
//
//
//
package com.development.task.mvvmproductapp.data.local

import android.annotation.SuppressLint
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


/**
 * Class which provides a model for Product
 * @constructor Sets all properties of the post
 * @property productId the unique identifier of the productId
 * @property id the unique identifier of the product in DB
 * @property title the title of the Product
 * @property price the content of the product
 * @property image the object of Image contains link,height and weight
 */
@Parcelize
@SuppressLint("ParcelCreator")
@Entity
data class ProductModel(
    @SerializedName("id")
    @Expose
    @PrimaryKey
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
    @Embedded var image: PImage

) :Parcelable