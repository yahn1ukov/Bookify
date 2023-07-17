package ua.yahniukov.bookify.domain.usecases.book

import ua.yahniukov.bookify.domain.repositories.BookRepository
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

class DeleteBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(bookUID: String): Result<Nothing> {
        return try {
            bookRepository.delete(bookUID)
            Result.Success()
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}