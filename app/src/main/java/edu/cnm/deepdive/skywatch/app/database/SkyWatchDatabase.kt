package edu.cnm.deepdive.skywatch.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import edu.cnm.deepdive.skywatch.app.database.SkyWatchDatabase.Companion.DATABASE_VERSION
import edu.cnm.deepdive.skywatch.app.model.dao.UserDao
import edu.cnm.deepdive.skywatch.app.model.entity.Favorite
import edu.cnm.deepdive.skywatch.app.model.entity.User
import java.net.URI
import java.net.URL
import kotlin.time.Instant

@Database(
    entities = [User::class, Favorite::class],
    version = DATABASE_VERSION
)
@TypeConverters(SkyWatchDatabase::class)
abstract class SkyWatchDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    // TODO: Add getter for FavoriteDao.

    companion object {

        const val DATABASE_NAME = "sky-watch-db"
        const val DATABASE_VERSION = 1

        @JvmStatic
        @TypeConverter
        fun instantToLong(value: Instant?): Long? =
            value?.toEpochMilliseconds()

        @JvmStatic
        @TypeConverter
        fun longToInstant(value: Long?): Instant? =
            value?.let { Instant.fromEpochMilliseconds(it) }

        @JvmStatic
        @TypeConverter
        fun urlToString(value: URL?): String? =
            value?.toString()

        @JvmStatic
        @TypeConverter
        fun stringToUrl(value: String?): URL? =
            value?.let { URI(it).toURL() }

    }

}
