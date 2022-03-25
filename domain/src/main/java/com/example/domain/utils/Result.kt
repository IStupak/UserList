package com.example.domain.utils

sealed class ResultOf<out R>(open val value: R?) {
    data class Success<out R>(override val value: R, val fromCache: Boolean = false) :
        ResultOf<R>(value)

    data class Failure(
        val message: String?,
        val throwable: Throwable?,
    ) : ResultOf<Nothing>(null)

    data class Loading<out R>(override val value: R? = null) : ResultOf<R>(null)
}