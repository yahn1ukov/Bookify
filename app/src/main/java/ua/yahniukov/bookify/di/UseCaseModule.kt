package ua.yahniukov.bookify.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.yahniukov.bookify.domain.repositories.AuthRepository
import ua.yahniukov.bookify.domain.repositories.BookRepository
import ua.yahniukov.bookify.domain.repositories.UserRepository
import ua.yahniukov.bookify.domain.usecases.auth.AuthUseCase
import ua.yahniukov.bookify.domain.usecases.auth.LogInUseCase
import ua.yahniukov.bookify.domain.usecases.auth.LogOutUseCase
import ua.yahniukov.bookify.domain.usecases.auth.RegisterUseCase
import ua.yahniukov.bookify.domain.usecases.auth.ResetPasswordUseCase
import ua.yahniukov.bookify.domain.usecases.book.BookUseCase
import ua.yahniukov.bookify.domain.usecases.book.CreateBookUseCase
import ua.yahniukov.bookify.domain.usecases.book.DeleteBookUseCase
import ua.yahniukov.bookify.domain.usecases.book.GetAllBooksUseCase
import ua.yahniukov.bookify.domain.usecases.user.GetCurrentUserUseCase
import ua.yahniukov.bookify.domain.usecases.user.UserUseCase

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideUserUseCase(
        userRepository: UserRepository
    ): UserUseCase {
        return UserUseCase(
            GetCurrentUserUseCase(userRepository)
        )
    }

    @Provides
    fun provideAuthUseCase(
        authRepository: AuthRepository
    ): AuthUseCase {
        return AuthUseCase(
            LogInUseCase(authRepository),
            RegisterUseCase(authRepository),
            ResetPasswordUseCase(authRepository),
            LogOutUseCase(authRepository)
        )
    }

    @Provides
    fun provideBookUseCase(
        bookRepository: BookRepository
    ): BookUseCase {
        return BookUseCase(
            GetAllBooksUseCase(bookRepository),
            CreateBookUseCase(bookRepository),
            DeleteBookUseCase(bookRepository)
        )
    }
}