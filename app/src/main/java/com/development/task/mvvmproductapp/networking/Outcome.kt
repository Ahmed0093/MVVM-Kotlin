package com.development.task.mvvmproductapp.networking

/**
 * Re-Used from Online Repository
 */
sealed class Outcome<T> {
    data class Progress<T>(var loading: Boolean) : Outcome<T>()
    data class Success<T>(var data: T) : Outcome<T>()

    data class Success2<T>(var data: T) : Outcome<List<T>>()

    data class Failure<T>(val e: Throwable) : Outcome<T>()

    companion object {
        fun <T> loading(isLoading: Boolean): Outcome<T> = Progress(isLoading)

        fun <T> success(data: T): Outcome<T> = Success(data)
        fun <T> success2(data: T): Outcome<List<T>> = Success2(data)

        fun <T> failure(e: Throwable): Outcome<T> = Failure(e)
    }
}