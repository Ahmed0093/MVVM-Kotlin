package com.development.task.mvvmproductapp.list.model

import android.util.Log
import com.development.task.mvvmproductapp.data.local.*
import com.development.task.mvvmproductapp.extensions.*
import com.development.task.mvvmproductapp.networking.Outcome
import com.development.task.mvvmproductapp.networking.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject


class ListRepository(
    private val local: ListDataContract.Local,
    private val remote: ListDataContract.Remote,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : ListDataContract.Repository {

    override val productFetchOutcome: PublishSubject<Outcome<Products>>
        get() = PublishSubject.create<Outcome<Products>>() //To change initializer of created properties use File | Settings | File Templates.


    override val postFetchOutcome: PublishSubject<Outcome<Products>> =
        PublishSubject.create<Outcome<Products>>()

    override fun fetchPosts() {
        postFetchOutcome.loading(true)
        local.getData()
            .performOnBackGroundOutOnMainThread(scheduler)
            .subscribe({ resultData ->
                postFetchOutcome.success(Products(resultData, resultData.count()))
            }, { error -> handleError(error) })
            .addTo(compositeDisposable)
    }

    override fun handleError(error: Throwable) {
        postFetchOutcome.failed(error)
    }

    override fun refreshPosts() {
        productFetchOutcome.loading(true)
        remote.getPosts()
            .performOnBackGroundOutOnMainThread(scheduler)
            .subscribe(this::handleResponse)
            .addTo(compositeDisposable)
    }

    private fun handleResponse(productsData: Products) {
        Log.d("tag", "list" + productsData.toString())
        local.saveProductLocal(productsData.data)
        postFetchOutcome.success(productsData)
    }

}