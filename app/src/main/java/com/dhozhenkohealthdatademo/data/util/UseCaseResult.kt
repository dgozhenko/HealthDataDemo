package com.dhozhenkohealthdatademo.data.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

sealed class UseCaseResult<out T> {
    data class Success<T>(val value: T) : UseCaseResult<T>()
    data class Error(val error: String) : UseCaseResult<Nothing>()
}

fun <T> Flow<UseCaseResult<T>>.onSuccess(action: suspend (T) -> Unit): Flow<UseCaseResult<T>> = transform { result ->
    if(result is UseCaseResult.Success<T>) {
        action(result.value)
    }
    return@transform emit(result)
}

fun <T> Flow<UseCaseResult<T>>.onError(action: suspend (String) -> Unit): Flow<UseCaseResult<T>> = transform { result ->
    if(result is UseCaseResult.Error) {
        action(result.error)
    }
    return@transform emit(result)
}