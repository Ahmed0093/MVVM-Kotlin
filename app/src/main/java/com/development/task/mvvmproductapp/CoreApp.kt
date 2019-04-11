package com.development.task.mvvmproductapp

import android.app.Application
import com.development.task.mvvmproductapp.di.AppModule
import com.development.task.mvvmproductapp.di.CoreComponent
import com.development.task.mvvmproductapp.di.DaggerCoreComponent
import com.facebook.stetho.Stetho

open class CoreApp : Application() {

    companion object {
        lateinit var coreComponent: CoreComponent
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
               coreComponent = DaggerCoreComponent.builder().appModule(AppModule(this)).build()

    }
}