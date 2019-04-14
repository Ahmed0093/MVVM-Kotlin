package com.development.task.mvvmproductapp.list.model
import com.development.task.mvvmproductapp.data.remote.ProductService
import com.development.task.mvvmproductapp.testing.DummyData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ListRemoteDataTest {
    private val productService = mock<ProductService>()

    /**
     * Test that [ProductService.getProducts] fetches the ProductsList from the remote Api
     * */
    @Test
    fun getPosts() {

        whenever(productService.getProducts()).thenReturn(
            Observable.just(
                DummyData.getDummy()
            )
        )

        ListRemoteData(productService).getProducts().test().run {
            assertNoErrors()
            assertEquals(values().size, 1)
            assertEquals(values()[0].data.size, 2)
            assertEquals(values()[0].data[0].title, "title1")
            assertEquals(values()[0].data[1].title, "title2")
        }
    }
}
