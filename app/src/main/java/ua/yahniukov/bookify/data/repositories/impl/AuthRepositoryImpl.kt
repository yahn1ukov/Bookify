package ua.yahniukov.bookify.data.repositories.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import ua.yahniukov.bookify.data.repositories.AuthRepository
import ua.yahniukov.bookify.dto.auth.LoginRequest
import ua.yahniukov.bookify.dto.auth.RegisterRequest
import ua.yahniukov.bookify.dto.auth.ResetPasswordRequest
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): Result<Nothing> {
        return try {
            val (email, password) = loginRequest
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success()
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): Result<Nothing> {
        return try {
            val (firstName, lastName, email, password) = registerRequest
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            result.user?.updateProfile(
                UserProfileChangeRequest.Builder().setDisplayName("$firstName $lastName").build()
            )?.await()
            Result.Success()
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Result<Nothing> {
        return try {
            val (email) = resetPasswordRequest
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