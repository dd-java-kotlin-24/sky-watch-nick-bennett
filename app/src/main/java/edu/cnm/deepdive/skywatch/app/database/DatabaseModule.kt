package edu.cnm.deepdive.skywatch.app.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.cnm.deepdive.skywatch.app.model.dao.FavoriteDao
import edu.cnm.deepdive.skywatch.app.model.dao.UserDao
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SkyWatchDatabase =
        Room.databaseBuilder(
            context,
            SkyWatchDatabase::class.java,
            SkyWatchDatabase.DATABASE_NAME
        )
//            .addCallback()  // Callback is used for preloading database, etc.
//            .addMigrations() // Migrations used for migrating from older to newer versions of database.
            .build()

    @Provides
    @Singleton
    fun provideUserDao(database: SkyWatchDatabase): UserDao =
        database.getUserDao()

    @Provides
    @Singleton
    fun provideFavoriteDao(database: SkyWatchDatabase): FavoriteDao =
        database.getFavoriteDao()

}