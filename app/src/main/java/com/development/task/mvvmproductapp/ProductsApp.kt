package com.development.task.mvvmproductapp

import android.app.Application
import com.development.task.mvvmproductapp.di.AppModule
import com.development.task.mvvmproductapp.di.AppComponent
import com.development.task.mvvmproductapp.di.DaggerAppComponent
import com.facebook.stetho.Stetho

open class ProductsApp : Application() {

    companion object {
        lateinit var coreComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
        initStetho()
    }



    private fun initStetho() {
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }

    private fun initDI() {
               coreComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

    }
}