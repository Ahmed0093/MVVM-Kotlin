package com.development.task.mvvmproductapp.list.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.development.task.mvvmproductapp.data.PostWithUser
import com.development.task.mvvmproductapp.networking.Outcome
import com.development.task.mvvmproductapp.list.model.ListDataContract
import io.reactivex.disposables.CompositeDisposable


class ListViewModel(private val repo: ListDataContract.Repository,
                    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

//    val postsOutcome: LiveData<Outcome<List<PostWithUser>>> by lazy {
//       // Convert publish subject to livedata
////        repo.postFetchOutcome(compositeDisposable)
//    }

    fun getPosts() {
//        if (postsOutcome.value == null)
            repo.fetchPosts()
    }

    fun refreshPosts() {
        repo.refreshPosts()
    }

    override fun onCleared() {
        super.onCleared()
        //clear the disposables when the viewmodel is cleared
        compositeDisposable.clear()
//        PostDH.destroyListComponent()
    }
}