package ua.yahniukov.bookify.domain.repositories

import android.net.Uri
import ua.yahniukov.bookify.data.models.entities.BookEntity

interface BookRepository {
    suspend fun create(
        image: Uri,
        name: String,
        author: String,
        description: String
    )

    suspend fun getAll(): List<BookEntity>
    suspend fun delete(bookUID: String)
    suspend fun deleteAll()
}