package com.development.task.mvvmproductapp.list.model

import android.util.Log
import com.development.task.mvvmproductapp.data.local.*
import com.development.task.mvvmproductapp.extensions.*
import com.development.task.mvvmproductapp.networking.Outcome
import com.development.task.mvvmproductapp.networking.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

/**
 * Repository abstracts the logic of fetching the data and persisting it for
 * offline. They are the data source.
 */
class ListRepository(
    private val local: ListDataContract.Local,
    private val remote: ListDataContract.Remote,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : ListDataContract.Repository {

    override val productRepoFetchOutcome: PublishSubject<Outcome<Products>> =
        PublishSubject.create<Outcome<Products>>()
    /**
     * Fetch the products from database
     */
    override fun fetchProducts() {
        productRepoFetchOutcome.loading(true)
        local.getData()
            .performOnBackGroundOutOnMainThread(scheduler)
            .subscribe({ resultData ->
                productRepoFetchOutcome.success(Products(resultData, resultData.count()))
            }, { error -> handleError(error) })
            .addTo(compositeDisposable)
    }

    override fun handleError(error: Throwable) {
        productRepoFetchOutcome.failed(error)
    }
    /**
     * Fetch the products from remoteApi
     */
    override fun refreshProducs() {
        productRepoFetchOutcome.loading(true)
        remote.getProducts()
            .performOnBackGroundOutOnMainThread(scheduler)
            .subscribe({ resultData ->
                productRepoFetchOutcome.success(handleResponse(resultData))
            }, { error -> handleError(error) })
            .addTo(compositeDisposable)
    }

    private fun handleResponse(productsData: Products) :Products {
        Log.d("tag", "list" + productsData.toString())
        local.saveProductLocal(productsData.data)
        return productsData
    }

}