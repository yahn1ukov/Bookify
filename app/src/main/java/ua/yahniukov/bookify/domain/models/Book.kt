package ua.yahniukov.bookify.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ua.yahniukov.bookify.data.models.entities.BookEntity

@Parcelize
data class Book(
    val uid: String,
    val imageUrl: String,
    val name: String,
    val author: String,
    val description: String
) : Parcelable {
    companion object {
        fun toBook(book: BookEntity): Book {
            return Book(
                book.uid,
                book.imageUrl,
                book.name,
                book.author,
                book.description
            )
        }
    }
}