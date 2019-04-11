//package com.development.task.mvvmproductapp
//
//import android.app.Application
//import com.development.task.mvvmproductapp.di.AppModule
//import com.development.task.mvvmproductapp.di.AppComponent
//import com.facebook.stetho.Stetho
//
//open class AppClass : Application() {
//
//    companion object {
//        lateinit var appComponent: AppComponent
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        initDI()
//        initStetho()
//    }
//
//
//
//    private fun initStetho() {
//        if (BuildConfig.DEBUG)
//            Stetho.initializeWithDefaults(this)
//    }
//
//    private fun initDI() {
////        appComponent = DaggerA.builder().appModule(AppModule(this)).build()
//     appComponent =   DaggerAppComponent.builder().appModule(AppModule(this)).build()
//    }
//}