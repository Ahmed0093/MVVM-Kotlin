package com.development.task.mvvmproductapp.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [ProductModel::class], version = 1,exportSchema = false)
abstract class ProductDb : RoomDatabase() {
    abstract fun postDao(): ProductDao

}
