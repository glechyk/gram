package com.glechyk.gram.delta.core.utils

sealed class ResultDomain<T> {

    data class Success<T>(val data: T) : ResultDomain<T>()

    data class Error<T>(val exception: Throwable) : ResultDomain<T>()

}
