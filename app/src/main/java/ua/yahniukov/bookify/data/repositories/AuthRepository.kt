package ua.yahniukov.bookify.data.repositories

import com.google.firebase.auth.FirebaseUser
import ua.yahniukov.bookify.utils.Result

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<FirebaseUser>
    suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Result<FirebaseUser>

    suspend fun resetPassword(email: String): Result<Nothing>

    fun logout()
}