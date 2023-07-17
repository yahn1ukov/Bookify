package ua.yahniukov.bookify.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import ua.yahniukov.bookify.domain.repositories.UserRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): FirebaseUser? {
        return userRepository.currentUser
    }
}