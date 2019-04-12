package com.development.task.mvvmproductapp.testing

import android.support.annotation.VisibleForTesting
import com.development.task.mvvmproductapp.networking.Scheduler
import io.reactivex.schedulers.Schedulers


@VisibleForTesting(otherwise = VisibleForTesting.NONE)
class TestScheduler : Scheduler {

    override fun mainThread(): io.reactivex.Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): io.reactivex.Scheduler {
        return Schedulers.trampoline()
    }
}