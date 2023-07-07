package ua.yahniukov.bookify.data.repositories

import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    val currentUser: FirebaseUser?
}