package com.development.task.mvvmproductapp.di

import android.content.Context
import android.content.SharedPreferences
import com.bumptech.glide.RequestManager
import com.development.task.mvvmproductapp.networking.Scheduler
import com.squareup.picasso.Picasso
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, StorageModule::class, ImageModule::class])
interface AppComponent {
    fun context(): Context
    fun retrofit(): Retrofit
    fun picasso(): Picasso
    fun glideRequestManager(): RequestManager
    fun sharedPreferences(): SharedPreferences
    fun scheduler(): Scheduler
}