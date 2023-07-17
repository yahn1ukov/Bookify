package ua.yahniukov.bookify.domain.usecases.book

import android.net.Uri
import ua.yahniukov.bookify.domain.repositories.BookRepository
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

class CreateBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(
        image: Uri,
        name: String,
        author: String,
        description: String
    ): Result<Nothing> {
        return try {
            bookRepository.create(image, name, author, description)
            Result.Success()
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}