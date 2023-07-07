package ua.yahniukov.bookify.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.yahniukov.bookify.data.repositories.AuthRepository
import ua.yahniukov.bookify.data.repositories.UserRepository
import ua.yahniukov.bookify.data.repositories.impls.AuthRepositoryImpl
import ua.yahniukov.bookify.data.repositories.impls.UserRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository = authRepositoryImpl

    @Provides
    @Singleton
    fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository = userRepositoryImpl
}