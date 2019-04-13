package com.development.task.mvvmproductapp.data.local

import android.annotation.SuppressLint
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@SuppressLint("ParcelCreator")
 data class PImage (
    @SerializedName("link") val link: String,
    @SerializedName("height") val height: String,
    @SerializedName("width") val width: String) : Parcelable