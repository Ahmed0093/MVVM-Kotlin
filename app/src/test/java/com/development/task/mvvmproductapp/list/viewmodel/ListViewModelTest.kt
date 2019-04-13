package com.development.task.mvvmproductapp.list.viewmodel

import android.arch.lifecycle.Observer
import com.development.task.mvvmproductapp.data.local.Products
import com.development.task.mvvmproductapp.list.model.ListDataContract
import com.development.task.mvvmproductapp.networking.Outcome
import com.nhaarman.mockitokotlin2.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import org.junit.Test
import org.junit.Before

class ListViewModelTest {
    private lateinit var viewModel: ListViewModel

    private val repo: ListDataContract.Repository = mock()
    private val outcome: Observer<Outcome<Products>> = mock()


    @Before
    fun init() {
        repo.productRepoFetchOutcome
        viewModel = ListViewModel(repo, CompositeDisposable())
        whenever(repo.productRepoFetchOutcome).doReturn(PublishSubject.create())
        viewModel.productsOutcome.observeForever(outcome)
    }

    /**
     * Test [ListViewModel.getProducts] triggers [ListDataContract.Repository.fetchProducts] &
     * from [ListDataContract.Repository.productRepoFetchOutcome]
     * */
    @Test
    fun getProductsTest_IF_NO_Cached() {
        whenever(viewModel.productsOutcome).thenReturn(null)
        viewModel.getProducts()
        verify(repo).fetchProducts()

    }

    /**
     *  Verify [ListViewModel.refreshProducs] triggers [ListDataContract.Repository.refreshViewModelProducts]
     */
    @Test
    fun refreshProducsTest() {
        viewModel.refreshViewModelProducts()
        verify(repo).refreshProducs()
    }
}