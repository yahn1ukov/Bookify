package ua.yahniukov.bookify.domain.usecases.auth

import ua.yahniukov.bookify.domain.repositories.AuthRepository
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String): Result<Nothing> {
        return try {
            authRepository.resetPassword(email)
            Result.Success()
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}