package ua.yahniukov.bookify.data.repositories

import ua.yahniukov.bookify.dto.home.BookRequest
import ua.yahniukov.bookify.utils.Result

interface BookRepository {
    suspend fun create(bookRequest: BookRequest): Result<Nothing>
}