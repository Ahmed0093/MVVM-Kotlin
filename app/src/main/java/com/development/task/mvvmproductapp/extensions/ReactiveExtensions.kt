package com.development.task.mvvmproductapp.extensions
import com.development.task.mvvmproductapp.networking.Scheduler
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Extension function to subscribe on the background thread and observe on the main thread for a [Completable]
 * */
fun Completable.performOnBackGroundOutOnMainThread(scheduler: Scheduler): Completable {
    return this.subscribeOn(scheduler.io())
        .observeOn(scheduler.mainThread())
}

/**
 * Extension function to subscribe on the background thread and observe on the main thread for a [Flowable]
 * */
fun <T> Flowable<T>.performOnBackGroundOutOnMainThread(scheduler: Scheduler): Flowable<T> {
    return this.subscribeOn(scheduler.io())
        .observeOn(scheduler.mainThread())
}

/**
 * Extension function to subscribe on the background thread and observe on the main thread  for a [Single]
 * */
fun <T> Single<T>.performOnBackGroundOutOnMainThread(scheduler: Scheduler): Single<T> {
    return this.subscribeOn(scheduler.io())
        .observeOn(scheduler.mainThread())
}

/**
 * Extension function to subscribe on the background thread and observe on the main thread for a [Observable]
 * */
fun <T> Observable<T>.performOnBackGroundOutOnMainThread(scheduler: Scheduler): Observable<T> {
    return this.subscribeOn(scheduler.io())
        .observeOn(scheduler.mainThread())
}


/**
 * Extension function to add a Disposable to a CompositeDisposable
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

/**
 * Extension function to subscribe on the background thread for a Flowable
 * */
fun <T> Flowable<T>.performOnBack(scheduler: Scheduler): Flowable<T> {
    return this.subscribeOn(scheduler.io())
}

/**
 * Extension function to subscribe on the background thread for a Completable
 * */
fun Completable.performOnBack(scheduler: Scheduler): Completable {
    return this.subscribeOn(scheduler.io())
}

/**
 * Extension function to subscribe on the background thread for a Observable
 * */
fun <T> Observable<T>.performOnBack(scheduler: Scheduler): Observable<T> {
    return this.subscribeOn(scheduler.io())
}