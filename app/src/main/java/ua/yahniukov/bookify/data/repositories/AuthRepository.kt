package ua.yahniukov.bookify.data.repositories

import com.google.firebase.auth.FirebaseUser
import ua.yahniukov.bookify.data.Resource

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Resource<FirebaseUser>

    fun logout()
}