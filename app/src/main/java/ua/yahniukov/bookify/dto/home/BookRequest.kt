package ua.yahniukov.bookify.dto.home

import android.net.Uri

data class BookRequest(
    var image: Uri,
    val name: String,
    val author: String,
    val description: String = ""
)