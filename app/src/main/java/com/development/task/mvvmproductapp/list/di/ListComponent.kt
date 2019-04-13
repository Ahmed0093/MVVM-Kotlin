package com.development.task.mvvmproductapp.list.di

import android.arch.persistence.room.Room
import android.content.Context
import com.bumptech.glide.RequestManager
import com.development.task.mvvmproductapp.constants.Constants
import com.development.task.mvvmproductapp.data.local.ProductDb
import com.development.task.mvvmproductapp.di.AppComponent
import com.development.task.mvvmproductapp.networking.Scheduler
import com.development.task.mvvmproductapp.data.remote.ProductService
import com.development.task.mvvmproductapp.list.ProductListAdapter
import com.development.task.mvvmproductapp.list.ProducListActivity
import com.development.task.mvvmproductapp.list.ProductDetailsActivity
import com.squareup.picasso.Picasso
import dagger.Component
import com.development.task.mvvmproductapp.list.model.ListDataContract
import com.development.task.mvvmproductapp.list.model.ListLocalData
import com.development.task.mvvmproductapp.list.model.ListRemoteData
import com.development.task.mvvmproductapp.list.model.ListRepository
import com.development.task.mvvmproductapp.list.viewmodel.ListViewModelFactory

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@ListScope
@Component(dependencies = [AppComponent::class], modules = [ListModule::class])
interface ListComponent {

    //Expose to dependent components
    fun productDb(): ProductDb

    fun productService(): ProductService
    fun picasso(): Picasso
    fun scheduler(): Scheduler

    fun inject(producListActivity: ProducListActivity)
    fun inject(productDetailsActivity: ProductDetailsActivity)

}

@Module
class ListModule {

    /*Adapter*/
    @Provides
    @ListScope
    fun adapter(glideRequestManager: RequestManager): ProductListAdapter = ProductListAdapter(glideRequestManager)

    /*ViewModel*/
    @Provides
    @ListScope
    fun listViewModelFactory(repository: ListDataContract.Repository, compositeDisposable: CompositeDisposable): ListViewModelFactory = ListViewModelFactory(repository,compositeDisposable)

    /*Repository*/
    @Provides
    @ListScope
    fun listRepo(local: ListDataContract.Local, remote: ListDataContract.Remote, scheduler: Scheduler, compositeDisposable: CompositeDisposable): ListDataContract.Repository = ListRepository(local, remote, scheduler, compositeDisposable)


    @Provides
    @ListScope
    fun remoteData(productService: ProductService): ListDataContract.Remote = ListRemoteData(productService)

    @Provides
    @ListScope
    fun localData(productDb: ProductDb, scheduler: Scheduler): ListDataContract.Local = ListLocalData(productDb, scheduler)

    @Provides
    @ListScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    /*Parent providers to dependents*/
    @Provides
    @ListScope
    fun productDb(context: Context): ProductDb = Room.databaseBuilder(context, ProductDb::class.java, Constants.Products.DB_NAME).build()

    @Provides
    @ListScope
    fun productService(retrofit: Retrofit): ProductService = retrofit.create(ProductService::class.java)
}