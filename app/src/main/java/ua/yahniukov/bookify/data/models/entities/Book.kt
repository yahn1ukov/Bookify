package ua.yahniukov.bookify.data.models.entities

data class Book(
    val image: String,
    val name: String,
    val author: String,
    val description: String,
    val ownerUID: String
)