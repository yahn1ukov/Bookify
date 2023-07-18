package ua.yahniukov.bookify.data.repositories

import android.net.Uri
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import ua.yahniukov.bookify.data.models.entities.BookEntity
import ua.yahniukov.bookify.domain.repositories.BookRepository
import ua.yahniukov.bookify.domain.repositories.UserRepository
import ua.yahniukov.bookify.utils.Constants.TABLE_BOOKS
import ua.yahniukov.bookify.utils.toList
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
    ) {
        val imageUrl = pushImageToStorage(image)
        val bookDBRef = db.reference.child(TABLE_BOOKS)
        val bookUID = bookDBRef.push().key!!
        val ownerUID = userRepository.currentUser!!.uid
        val bookEntity = BookEntity(bookUID, imageUrl, name, author, description)
        bookDBRef.child(ownerUID).child(bookUID).setValue(bookEntity).await()
    }

    private suspend fun pushImageToStorage(image: Uri): String {
        val storageRef = storage.reference
        val imageName = UUID.randomUUID().toString()
        val imagePath = storageRef.child(imageName)
        imagePath.putFile(image).await()
        return imagePath.downloadUrl.await().toString()
    }

    override suspend fun getAll(): List<BookEntity> {
        val bookDBRef = db.reference.child(TABLE_BOOKS)
        val ownerUID = userRepository.currentUser!!.uid
        return bookDBRef.child(ownerUID).get().await().toList()
    }

    override suspend fun delete(bookUID: String) {
        val bookDBRef = db.reference.child(TABLE_BOOKS)
        val ownerUID = userRepository.currentUser!!.uid
        bookDBRef.child(ownerUID).child(bookUID).removeValue().await()
    }
}