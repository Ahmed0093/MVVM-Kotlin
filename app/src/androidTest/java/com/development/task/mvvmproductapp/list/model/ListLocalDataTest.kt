package com.development.task.mvvmproductapp.list.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.development.task.mvvmproductapp.data.local.ProductDb
import com.development.task.mvvmproductapp.testing.DummyData
import com.development.task.mvvmproductapp.testing.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListLocalDataTest {
    private lateinit var productDb: ProductDb

    private val listLocalData: ListLocalData by lazy { ListLocalData(productDb, TestScheduler()) }

    //Necessary for Room insertions to work
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dummyProductsList = DummyData.getDummy().data

    @Before
    fun init() {
        productDb = Room
            .inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, ProductDb::class.java)
            .allowMainThreadQueries()
            .addMigrations()
            .build()
    }

    /**
     * Test that [ListLocalData.getData] fetches the ProductsList from the database
     * */
    @Test
    fun getDataTest() {
        productDb.postDao().insertAll(dummyProductsList)

        listLocalData.getData().test().assertValue(dummyProductsList)
    }


    /**
     * Test that [ListLocalData.saveProductLocal] saves the passed lists into the database
     * */
    @Test
    fun saveProductListTest() {

        listLocalData.saveProductLocal(dummyProductsList)

        val products = productDb.postDao().getAll()
        products.test().assertNoErrors().assertValue(dummyProductsList)

    }

    @After
    fun clean() {
        productDb.close()
    }

}