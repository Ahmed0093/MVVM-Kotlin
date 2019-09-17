package com.development.task.mvvmproductapp.list.model

import com.development.task.mvvmproductapp.data.adsdata.AdsModel
import com.development.task.mvvmproductapp.data.local.ProductModel
import com.development.task.mvvmproductapp.data.local.ProductDb
import com.development.task.mvvmproductapp.extensions.performOnBack
import com.development.task.mvvmproductapp.networking.Scheduler

import io.reactivex.Completable
import io.reactivex.Flowable

class ListLocalData(private val postDb: ProductDb, private val scheduler: Scheduler) : ListDataContract.Local {
    override fun saveAdsLocal(adsModelList: List<AdsModel>) {
        Completable.fromAction {
            postDb.adsDao().insertAll(adsModelList)

        }
            .performOnBack(scheduler)
            .subscribe()    }

    override fun getAdsData(): Flowable<List<AdsModel>> {
       return postDb.adsDao().getAll()
    }

    override fun getData(): Flowable<List<ProductModel>> {
        return postDb.postDao().getAll()
    }

    override fun saveProductLocal(productModelList: List<ProductModel>) {
        Completable.fromAction {
                        postDb.postDao().insertAll(productModelList)

        }
                .performOnBack(scheduler)
                .subscribe()
    }
}