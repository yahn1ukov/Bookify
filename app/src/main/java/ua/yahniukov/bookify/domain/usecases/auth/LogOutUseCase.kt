package ua.yahniukov.bookify.domain.usecases.auth

import ua.yahniukov.bookify.domain.repositories.AuthRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke() {
        authRepository.logout()
    }
}