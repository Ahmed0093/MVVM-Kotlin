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
    private val postService = mock<ProductService>()

    @Test
    fun getPosts() {

        whenever(postService.getProducts()).thenReturn(
            Observable.just(
                DummyData.getDummy()
            )
        )

        ListRemoteData(postService).getPosts().test().run {
            assertNoErrors()
            assertEquals(values().size, 1)
            assertEquals(values()[0].data.size, 2)
            assertEquals(values()[0].data[0].title, "title1")
            assertEquals(values()[0].data[1].title, "title2")
        }
    }
}
//    @Test
//    fun getPosts_IF_Failure() {
//
//        whenever(postService.getPosts()).thenReturn(
//            Observable.just(throw error("error"))
//        )
//
//        ListRemoteData(postService).getPosts().test().run {
//            assertError(throw error("error"))
//        }
//    }
//}