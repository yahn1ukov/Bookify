package ua.yahniukov.bookify.data.repositories.impl

import android.net.Uri
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import ua.yahniukov.bookify.data.models.entities.Book
import ua.yahniukov.bookify.data.repositories.BookRepository
import ua.yahniukov.bookify.data.repositories.UserRepository
import ua.yahniukov.bookify.utils.Result
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepositoryImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val db: FirebaseDatabase,
    private val storage: FirebaseStorage
) : BookRepository {
    override suspend fun create(
        image: Uri,
        name: String,
        author: String,
        description: String
    ): Result<Nothing> {
        return try {
            val storageRef = storage.reference
            val imageName = UUID.randomUUID().toString()
            val imagePath = storageRef.child(imageName)
            imagePath.putFile(image).await()
            val bookDBRef = db.getReference("Books")
            val bookID = bookDBRef.push().key!!
            val ownerUID = userRepository.currentUser!!.uid
            val book = Book(
                imagePath.downloadUrl.await().toString(),
                name, author, description, ownerUID
            )
            bookDBRef.child(bookID).setValue(book).await()
            Result.Success()
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}