package ua.yahniukov.bookify.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ua.yahniukov.bookify.domain.repositories.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    auth: FirebaseAuth
) : UserRepository {
    override val currentUser: FirebaseUser? = auth.currentUser
}