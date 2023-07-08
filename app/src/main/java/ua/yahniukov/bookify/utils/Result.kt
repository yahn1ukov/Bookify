package ua.yahniukov.bookify.utils

sealed class Result<out R> {
    data class Success<out R>(val data: R? = null) : Result<R>()
    data class Failure(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}