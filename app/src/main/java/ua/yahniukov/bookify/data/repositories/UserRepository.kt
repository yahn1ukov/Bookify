package ua.yahniukov.bookify.data.repositories

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import ua.yahniukov.bookify.utils.Result

interface UserRepository {
    val currentUser: FirebaseUser?

    suspend fun updateProfilePhoto(image: Uri): Result<Nothing>
}