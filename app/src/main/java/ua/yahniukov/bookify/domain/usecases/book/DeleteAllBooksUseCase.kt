package ua.yahniukov.bookify.domain.usecases.book

import ua.yahniukov.bookify.domain.repositories.BookRepository
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

class DeleteAllBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(): Result<Nothing> {
        return try {
            bookRepository.deleteAll()
            Result.Success()
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}