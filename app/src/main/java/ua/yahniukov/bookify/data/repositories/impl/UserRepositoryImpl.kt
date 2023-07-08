package ua.yahniukov.bookify.data.repositories.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ua.yahniukov.bookify.data.repositories.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : UserRepository {
    override val currentUser: FirebaseUser?
        get() = auth.currentUser
}