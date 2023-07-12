package ua.yahniukov.bookify.data.repositories.impl

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import ua.yahniukov.bookify.data.repositories.UserRepository
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    auth: FirebaseAuth
) : UserRepository {
    override val currentUser: FirebaseUser? = auth.currentUser

    override suspend fun updateProfilePhoto(image: Uri): Result<Nothing> {
        return try {
            currentUser?.updateProfile(
                UserProfileChangeRequest.Builder().setPhotoUri(image).build()
            )?.await()
            Result.Success()
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}