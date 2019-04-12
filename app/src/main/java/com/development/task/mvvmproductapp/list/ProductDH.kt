package com.development.task.mvvmproductapp.list

import com.development.task.mvvmproductapp.ProductsApp
import com.development.task.mvvmproductapp.list.di.DaggerListComponent
import com.development.task.mvvmproductapp.list.di.ListComponent

import javax.inject.Singleton

@Singleton
object ProductDH {
    private var listComponent: ListComponent? = null
    fun listComponent(): ListComponent {
        if (listComponent == null)
            listComponent = DaggerListComponent.builder().appComponent(ProductsApp.coreComponent).build()
        return listComponent as ListComponent
    }

    fun destroyListComponent() {
        listComponent = null
    }

}