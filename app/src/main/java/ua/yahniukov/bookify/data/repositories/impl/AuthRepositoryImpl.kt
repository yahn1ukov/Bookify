package ua.yahniukov.bookify.data.repositories.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import ua.yahniukov.bookify.data.repositories.AuthRepository
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {
    override suspend fun login(email: String, password: String): Result<Nothing> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success()
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Result<Nothing> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            result.user?.updateProfile(
                UserProfileChangeRequest.Builder().setDisplayName("$firstName $lastName").build()
            )?.await()
            Result.Success()
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun resetPassword(email: String): Result<Nothing> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Result.Success()
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun logout() {
        auth.signOut()
    }
}