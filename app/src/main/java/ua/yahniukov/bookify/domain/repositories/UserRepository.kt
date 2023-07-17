package ua.yahniukov.bookify.domain.repositories

import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    val currentUser: FirebaseUser?
}