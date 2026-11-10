package edu.cnm.deepdive.skywatch.app.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.net.URL
import kotlin.time.Instant

@Entity(
    tableName = "favorite",
    indices = [
        Index(value = ["external_key"], unique = true)
    ]
)
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "favorite_id")
    val id: Long = 0,

    @ColumnInfo(name = "external_key")
    val externalKey: String,

    val description: String,
    val thumbnail: URL,
    val image: URL,
    val favorited: Instant,
)
