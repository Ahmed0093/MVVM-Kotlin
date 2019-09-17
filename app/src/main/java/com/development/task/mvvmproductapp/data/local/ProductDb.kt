package com.development.task.mvvmproductapp.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.development.task.mvvmproductapp.data.adsdata.AdsDao
import com.development.task.mvvmproductapp.data.adsdata.AdsModel

@Database(entities = [ProductModel::class,AdsModel::class], version = 1,exportSchema = false)
abstract class ProductDb : RoomDatabase() {
    abstract fun postDao(): ProductDao
    abstract fun adsDao(): AdsDao


}
