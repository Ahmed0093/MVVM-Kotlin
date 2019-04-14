package com.development.task.mvvmproductapp.list.model

import com.development.task.mvvmproductapp.data.local.*
import com.development.task.mvvmproductapp.networking.Outcome
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
/**
* A ListDataContract  . Methods in this interface support fetching and saving the data
 * whether from a remote api or from local data base
*/
interface ListDataContract {
    interface Repository {
        val productRepoFetchOutcome: PublishSubject<Outcome<Products>>
        fun fetchProducts()
        fun refreshProducs()
        fun handleError(error: Throwable)
    }

    interface Local {
        fun saveProductLocal(productModelList: List<ProductModel>)
        fun getData(): Flowable<List<ProductModel>>
    }

    interface Remote {
        fun getProducts(): Observable<Products>
    }
}