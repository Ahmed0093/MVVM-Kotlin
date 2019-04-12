package com.development.task.mvvmproductapp.list.model

import android.content.Context
import com.development.task.mvvmproductapp.data.local.*
import com.development.task.mvvmproductapp.networking.Outcome

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

interface ListDataContract {
    interface Repository {
        val postFetchOutcome: PublishSubject<Outcome<Products>>
        val productFetchOutcome: PublishSubject<Outcome<Products>>
        fun fetchPosts()
        fun refreshPosts()
        fun handleError(error: Throwable)
    }

    interface Local {
        fun saveProductLocal(datums: List<ProductModel>)
        fun getData(): Flowable<List<ProductModel>>
    }

    interface Remote {
        fun getPosts(): Observable<Products>
    }
}