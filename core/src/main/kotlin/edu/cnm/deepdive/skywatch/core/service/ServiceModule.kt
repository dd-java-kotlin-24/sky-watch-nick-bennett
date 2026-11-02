package edu.cnm.deepdive.skywatch.core.service

import dagger.Module
import dagger.Provides
import edu.cnm.deepdive.skywatch.core.repository.NasaRepository
import jakarta.inject.Singleton

@Module
object ServiceModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideMediaSearchService(): MediaSearchService =
        MediaSearchServiceImpl(NasaRepository)

}