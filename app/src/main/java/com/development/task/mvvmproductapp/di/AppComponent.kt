//package com.development.task.mvvmproductapp.di
//
//import android.content.Context
//import android.content.SharedPreferences
//import com.squareup.picasso.Picasso
//import dagger.Component
//import io.reactivex.Scheduler
//import retrofit2.Retrofit
//import javax.inject.Singleton
//
//@Singleton
//@Component(modules = [AppModule::class, NetworkModule::class, StorageModule::class, ImageModule::class])
//interface AppComponent {
//
//    fun context(): Context
//
//    fun retrofit(): Retrofit
//
//    fun picasso(): Picasso
//
//    fun sharedPreferences(): SharedPreferences
//
//    fun scheduler(): Scheduler
//    @Component.Builder
//    interface Builder {
//        fun build(): AppComponent
//
//        fun appModule(appModule: AppModule): Builder
//    }
//}