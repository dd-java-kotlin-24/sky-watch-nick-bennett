package edu.cnm.deepdive.skywatch.app.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsUserRepository(repository: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun bindsFavoriteRepository(repository: FavoriteRepositoryImpl): FavoriteRepository

}