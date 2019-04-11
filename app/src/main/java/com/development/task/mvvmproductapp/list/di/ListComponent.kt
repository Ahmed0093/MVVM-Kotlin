package com.development.task.mvvmproductapp.list.di

import android.arch.persistence.room.Room
import android.content.Context
import com.development.task.mvvmproductapp.Constants
import com.development.task.mvvmproductapp.data.local.ProductDb
import com.development.task.mvvmproductapp.di.CoreComponent
import com.development.task.mvvmproductapp.list.PostListAdapter
import com.development.task.mvvmproductapp.networking.Scheduler
import com.development.task.mvvmproductapp.data.remote.PostService
import com.development.task.mvvmproductapp.list.ProducListActivity
import com.karntrehan.posts.list.di.ListScope
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
@Component(dependencies = [CoreComponent::class], modules = [ListModule::class])
interface ListComponent {

    //Expose to dependent components
    fun productDb(): ProductDb

    fun postService(): PostService
    fun picasso(): Picasso
    fun scheduler(): Scheduler

    fun inject(producListActivity: ProducListActivity)
}

@Module
@ListScope
class ListModule {

    /*Adapter*/
    @Provides
    @ListScope
    fun adapter(picasso: Picasso): PostListAdapter = PostListAdapter(picasso)

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
    fun remoteData(postService: PostService): ListDataContract.Remote = ListRemoteData(postService)

    @Provides
    @ListScope
    fun localData(postDb: ProductDb, scheduler: Scheduler): ListDataContract.Local = ListLocalData(postDb, scheduler)

    @Provides
    @ListScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    /*Parent providers to dependents*/
    @Provides
    @ListScope
    fun productDb(context: Context): ProductDb = Room.databaseBuilder(context, ProductDb::class.java, Constants.Products.DB_NAME).build()

    @Provides
    @ListScope
    fun productService(retrofit: Retrofit): PostService = retrofit.create(PostService::class.java)
}