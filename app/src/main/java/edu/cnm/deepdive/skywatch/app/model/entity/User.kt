package edu.cnm.deepdive.skywatch.app.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.net.URL
import kotlin.time.Instant

@Entity(
    tableName = "user",
    indices = [
        Index(value = ["oauth_key"], unique = true),
        Index(value = ["display_name"], unique = true)
    ]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id: Long = 0,

    @ColumnInfo(name = "oauth_key")
    val oauthKey: String,

    @ColumnInfo(name = "display_name")
    val displayName: String,

    val avatar: URL? = null,
    val created: Instant,
)