package ua.yahniukov.bookify.data.repositories

import ua.yahniukov.bookify.utils.Result

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Nothing>
    suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Result<Nothing>

    suspend fun resetPassword(email: String): Result<Nothing>
    fun logout()
}