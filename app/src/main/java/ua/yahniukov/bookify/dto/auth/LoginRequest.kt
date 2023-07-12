package ua.yahniukov.bookify.dto.auth

data class LoginRequest(
    val email: String,
    val password: String
)