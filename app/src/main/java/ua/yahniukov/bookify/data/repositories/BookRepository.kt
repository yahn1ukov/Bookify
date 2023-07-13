package ua.yahniukov.bookify.data.repositories

import android.net.Uri
import ua.yahniukov.bookify.data.models.entities.Book
import ua.yahniukov.bookify.utils.Result

interface BookRepository {
    suspend fun create(
        image: Uri,
        name: String,
        author: String,
        description: String = ""
    ): Result<Nothing>

    suspend fun getAll(): Result<List<Book>>
}