package com.development.task.mvvmproductapp.data.local

import android.arch.persistence.room.*
import io.reactivex.Flowable


@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun upsertAll(products: List<ProductModel>)

    @Delete
    fun delete(product: ProductModel)

    @Query("SELECT * FROM ProductModel")
    fun getAll(): Flowable<List<ProductModel>>
}