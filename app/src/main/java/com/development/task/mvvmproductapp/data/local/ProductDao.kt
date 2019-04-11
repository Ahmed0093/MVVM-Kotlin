package com.development.task.mvvmproductapp.data.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.development.task.mvvmproductapp.data.local.Product
import io.reactivex.Flowable


@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertAll(products: List<Product>)

    @Delete
    fun delete(product: Product)

    @Query("SELECT * FROM product")
    fun getAll(): LiveData<List<Product>>
}