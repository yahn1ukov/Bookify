package ua.yahniukov.bookify.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.yahniukov.bookify.data.repositories.AuthRepository
import ua.yahniukov.bookify.data.repositories.UserRepository
import ua.yahniukov.bookify.data.repositories.impl.AuthRepositoryImpl
import ua.yahniukov.bookify.data.repositories.impl.UserRepositoryImpl

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
}