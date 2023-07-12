package ua.yahniukov.bookify.data.repositories

import ua.yahniukov.bookify.dto.auth.LoginRequest
import ua.yahniukov.bookify.dto.auth.RegisterRequest
import ua.yahniukov.bookify.dto.auth.ResetPasswordRequest
import ua.yahniukov.bookify.utils.Result

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): Result<Nothing>
    suspend fun register(registerRequest: RegisterRequest): Result<Nothing>
    suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): Result<Nothing>
    fun logout()
}