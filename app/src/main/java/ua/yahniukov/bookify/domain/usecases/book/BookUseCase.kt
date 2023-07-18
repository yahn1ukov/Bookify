package ua.yahniukov.bookify.domain.usecases.book

data class BookUseCase(
    val getAllBooksUseCase: GetAllBooksUseCase,
    val createBookUseCase: CreateBookUseCase,
    val deleteBookUseCase: DeleteBookUseCase
)