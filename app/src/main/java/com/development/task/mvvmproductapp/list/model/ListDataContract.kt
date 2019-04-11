package com.development.task.mvvmproductapp.list.model

import android.arch.lifecycle.LiveData
import com.development.task.mvvmproductapp.data.PostWithUser
import com.development.task.mvvmproductapp.data.local.Product
import com.development.task.mvvmproductapp.data.local.ProductData
import com.development.task.mvvmproductapp.networking.Outcome

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

interface ListDataContract {
    interface Repository {
        val postFetchOutcome: LiveData<Outcome<List<PostWithUser>>>
        fun fetchPosts()
        fun refreshPosts()
//        fun saveUsersAndPosts(users: List<User>, posts: List<Post>)
        fun handleError(error: Throwable)
    }

    interface Local {
        fun getPostsWithUsers(): LiveData<List<Product>>
//        fun saveUsersAndPosts(users: List<User>, posts: List<Post>)
    }

    interface Remote {
//        fun getUsers(): Single<List<User>>
        fun getPosts(): Single<List<Product>>
    }
}