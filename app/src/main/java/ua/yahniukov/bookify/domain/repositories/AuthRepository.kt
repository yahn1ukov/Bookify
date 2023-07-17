package ua.yahniukov.bookify.domain.repositories

interface AuthRepository {
    suspend fun login(email: String, password: String)
    suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    )

    suspend fun resetPassword(email: String)
    fun logout()
}