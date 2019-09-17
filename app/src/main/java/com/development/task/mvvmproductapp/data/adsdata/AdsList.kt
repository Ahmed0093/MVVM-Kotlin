package com.development.task.mvvmproductapp.data.adsdata

import com.google.gson.annotations.SerializedName

data class AdsList (@SerializedName("data") val data: List<AdsModel>)
