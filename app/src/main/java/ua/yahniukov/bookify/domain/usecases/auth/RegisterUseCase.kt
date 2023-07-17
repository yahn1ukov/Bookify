package ua.yahniukov.bookify.domain.usecases.auth

import ua.yahniukov.bookify.domain.repositories.AuthRepository
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Result<Nothing> {
        return try {
            authRepository.register(firstName, lastName, email, password)
            Result.Success()
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}