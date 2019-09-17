package com.development.task.mvvmproductapp.data.adsdata

import android.arch.persistence.room.*
import io.reactivex.Flowable
@Dao
interface AdsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(ads: List<AdsModel>)

    @Delete
    fun delete(ads: List<AdsModel>)

    @Query("SELECT * FROM AdsModel")
    fun getAll(): Flowable<List<AdsModel>>
}
