package ua.yahniukov.bookify.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.yahniukov.bookify.domain.repositories.AuthRepository
import ua.yahniukov.bookify.domain.repositories.BookRepository
import ua.yahniukov.bookify.domain.repositories.UserRepository
import ua.yahniukov.bookify.data.repositories.AuthRepositoryImpl
import ua.yahniukov.bookify.data.repositories.BookRepositoryImpl
import ua.yahniukov.bookify.data.repositories.UserRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth
    ): AuthRepository {
        return AuthRepositoryImpl(auth)
    }

    @Provides
    fun provideUserRepository(
        auth: FirebaseAuth
    ): UserRepository {
        return UserRepositoryImpl(auth)
    }

    @Provides
    fun provideBookRepository(
        userRepository: UserRepository,
        db: FirebaseDatabase,
        storage: FirebaseStorage
    ): BookRepository {
        return BookRepositoryImpl(userRepository, db, storage)
    }
}