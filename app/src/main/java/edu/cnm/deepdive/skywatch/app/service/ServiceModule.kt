package edu.cnm.deepdive.skywatch.app.service

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ServiceModule {

    @Binds
    @Singleton
    fun bindFavoritesService(implementation: FavoritesServiceImpl): FavoritesService

}