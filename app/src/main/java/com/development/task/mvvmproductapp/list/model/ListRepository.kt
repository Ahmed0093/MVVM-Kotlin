package com.development.task.mvvmproductapp.list.model

import android.arch.lifecycle.LiveData
import com.development.task.mvvmproductapp.data.PostWithUser
import com.development.task.mvvmproductapp.networking.Outcome
import com.development.task.mvvmproductapp.networking.Scheduler

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject


class ListRepository(
    private val local: ListDataContract.Local,
    private val remote: ListDataContract.Remote,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
    //    override val postFetchOutcome: LiveData<Outcome<List<PostWithUser>>>
) : ListDataContract.Repository {
    override val postFetchOutcome: LiveData<Outcome<List<PostWithUser>>>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun fetchPosts() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun handleError(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshPosts() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override val postFetchOutcome: PublishSubject<Outcome<List<PostWithUser>>> =
//        PublishSubject.create<Outcome<List<PostWithUser>>>()

//    override fun fetchPosts() {
//        postFetchOutcome.loading(true)
//        //Observe changes to the db
//        local.getPostsWithUsers()
//            .performOnBackOutOnMain(scheduler)
//            .doAfterNext {
////                if (Synk.shouldSync(SynkKeys.POSTS_HOME, 2, TimeUnit.HOURS))
////                    refreshPosts()
//            }
//            .subscribe({ postsWithUsers ->
//                postFetchOutcome.success(postsWithUsers)
//            }, { error -> handleError(error) })
//            .addTo(compositeDisposable)
//    }

//    override fun refreshPosts() {
//        postFetchOutcome.loading(true)
//        Single.zip(
//            remote.getUsers(),
//            remote.getPosts(),
//            zipUsersAndPosts()
//        )
//            .performOnBackOutOnMain(scheduler)
////            .updateSynkStatus(key = SynkKeys.POSTS_HOME)
//            .subscribe({}, { error -> handleError(error) })
//            .addTo(compositeDisposable)
//    }

//    private fun zipUsersAndPosts() =
//        BiFunction<List<User>, List<Post>, Unit> { users, posts ->
//            saveUsersAndPosts(users, posts)
//        }

//    override fun saveUsersAndPosts(users: List<User>, posts: List<Post>) {
//        local.saveUsersAndPosts(users, posts)
//    }

//    override fun handleError(error: Throwable) {
//        postFetchOutcome.failed(error)
//    }

}