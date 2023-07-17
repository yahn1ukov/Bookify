package ua.yahniukov.bookify.domain.usecases.book

import ua.yahniukov.bookify.domain.models.Book
import ua.yahniukov.bookify.domain.repositories.BookRepository
import ua.yahniukov.bookify.utils.Result
import javax.inject.Inject

class GetAllBooksUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(): Result<List<Book>> {
        return try {
            val books = bookRepository.getAll()
            val booksDto = books.map { Book.toBook(it) }
            Result.Success(booksDto)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}