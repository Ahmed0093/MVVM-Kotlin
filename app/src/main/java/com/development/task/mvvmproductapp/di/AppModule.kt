package com.development.task.mvvmproductapp.di

import android.content.Context
import com.development.task.mvvmproductapp.networking.AppScheduler
import com.development.task.mvvmproductapp.networking.Scheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {
    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun scheduler(): Scheduler {
        return AppScheduler()
    }
}