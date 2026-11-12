package edu.cnm.deepdive.skywatch.app.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.net.URL
import kotlin.time.Instant

@Entity(
    tableName = "favorite",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["user_id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
        )
    ],
    indices = [
        Index(value = ["external_key"], unique = true),
        Index(value = ["user_id"])
    ]
)
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "favorite_id")
    val id: Long = 0,

    @ColumnInfo(name = "external_key")
    val externalKey: String,

    @ColumnInfo(collate = ColumnInfo.NOCASE)
    val description: String,

    val thumbnail: URL,
    val image: URL,
    val favorited: Instant,

    @ColumnInfo(name = "user_id")
    val userId: Long,
)
