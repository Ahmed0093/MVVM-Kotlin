package com.development.task.mvvmproductapp.list

import com.development.task.mvvmproductapp.CoreApp
import com.development.task.mvvmproductapp.list.di.DaggerListComponent
import com.development.task.mvvmproductapp.list.di.ListComponent

import javax.inject.Singleton

@Singleton
object PostDH {
    private var listComponent: ListComponent? = null
//    private var detailsComponent: DetailsComponent? = null

    fun listComponent(): ListComponent {
        if (listComponent == null)
            listComponent = DaggerListComponent.builder().coreComponent(CoreApp.coreComponent).build()
        return listComponent as ListComponent
    }

    fun destroyListComponent() {
        listComponent = null
    }

//    fun detailsComponent(): DetailsComponent {
//        if (detailsComponent == null)
//            detailsComponent = DaggerDetailsComponent.builder().listComponent(listComponent()).build()
//        return detailsComponent as DetailsComponent
//    }
//
//    fun destroyDetailsComponent() {
//        detailsComponent = null
//    }
}