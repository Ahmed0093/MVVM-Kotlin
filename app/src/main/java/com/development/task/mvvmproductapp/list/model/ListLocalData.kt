package com.development.task.mvvmproductapp.list.model

import com.development.task.mvvmproductapp.data.local.ProductModel
import com.development.task.mvvmproductapp.data.local.ProductDb
import com.development.task.mvvmproductapp.extensions.performOnBack
import com.development.task.mvvmproductapp.networking.Scheduler

import io.reactivex.Completable
import io.reactivex.Flowable

class ListLocalData(private val postDb: ProductDb, private val scheduler: Scheduler) : ListDataContract.Local {

    override fun getData(): Flowable<List<ProductModel>> {
        return postDb.postDao().getAll()
    }

    override fun saveProductLocal(productModelList: List<ProductModel>) {
        Completable.fromAction {
                        postDb.postDao().upsertAll(productModelList)

        }
                .performOnBack(scheduler)
                .subscribe()
    }
}