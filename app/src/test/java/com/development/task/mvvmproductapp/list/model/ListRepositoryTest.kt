package com.development.task.mvvmproductapp.list.model

import com.development.task.mvvmproductapp.data.remote.ProductService
import com.development.task.mvvmproductapp.testing.DummyData
import com.development.task.mvvmproductapp.testing.TestScheduler
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Test

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

    /**
     * Verify that [ListRepository.fetchProducts] Calls [ListDataContract.Local.getData]
      * */
    @Test
    fun fetchPosts() {
        val postWithUsersSuccess = DummyData.getDummy().data
        whenever(local.getData()).doReturn(Flowable.just(postWithUsersSuccess))
        repository.fetchProducts()
        verify(local).getData()
    }
    /**
     * Verify that [ListRepository.refreshProducs] Calls [ListDataContract.Remote.getProducts]
     * */
    @Test
    fun refreshPosts() {
        whenever(remote.getProducts()).doReturn(Observable.just(DummyData.getDummy()))
        repository.refreshProducs()
        verify(remote).getProducts()

    }
}