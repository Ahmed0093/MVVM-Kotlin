package com.development.task.mvvmproductapp.list.model

import com.development.task.mvvmproductapp.testing.DummyData
import com.development.task.mvvmproductapp.testing.TestScheduler
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.TestObserver
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ListRepositoryTest {
    private val local: ListDataContract.Local = mock()
    private val remote: ListDataContract.Remote = mock()

    private lateinit var repository: ListRepository
    private val compositeDisposable = CompositeDisposable()

    @Before
    fun init() {
        repository = ListRepository(local, remote, TestScheduler(), compositeDisposable)
    }


    @Test
    fun fetchPosts() {
        val postWithUsersSuccess = DummyData.getDummy().data
        whenever(local.getData()).doReturn(Flowable.just(postWithUsersSuccess))
        repository.fetchPosts()
        verify(local).getData()
    }

    @Test
    fun refreshPosts() {
        whenever(remote.getPosts()).doReturn(Observable.just(DummyData.getDummy()))
        repository.refreshPosts()
        verify(remote).getPosts()

    }
}