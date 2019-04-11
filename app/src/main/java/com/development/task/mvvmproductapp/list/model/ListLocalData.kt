package com.development.task.mvvmproductapp.list.model

import android.arch.lifecycle.LiveData
import com.development.task.mvvmproductapp.data.PostWithUser
import com.development.task.mvvmproductapp.data.local.Product
import com.development.task.mvvmproductapp.data.local.ProductDb
import com.development.task.mvvmproductapp.list.model.ListDataContract
import com.development.task.mvvmproductapp.networking.Scheduler

import io.reactivex.Completable
import io.reactivex.Flowable

class ListLocalData(private val postDb: ProductDb, private val scheduler: Scheduler) : ListDataContract.Local {

    override fun getPostsWithUsers(): LiveData<List<Product>> {
        return postDb.postDao().getAll()
    }

//    override fun saveUsersAndPosts(users: List<User>, posts: List<Post>) {
//        Completable.fromAction {
//            postDb.userDao().upsertAll(users)
//            postDb.postDao().upsertAll(posts)
//        }
//                .performOnBack(scheduler)
//                .subscribe()
//    }
}