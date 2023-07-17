package ua.yahniukov.bookify.domain.usecases.auth

data class AuthUseCase(
    val logInUseCase: LogInUseCase,
    val registerUseCase: RegisterUseCase,
    val resetPasswordUseCase: ResetPasswordUseCase,
    val logOutUseCase: LogOutUseCase
)