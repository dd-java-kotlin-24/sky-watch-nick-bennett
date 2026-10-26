package edu.cnm.deepdive.skywatch.core.model.dto.nasa

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.OffsetDateTime

@JsonClass(generateAdapter = true)
data class ItemData(
    val center: String,
    @Json(name = "date_created") val dateCreated: OffsetDateTime,
    val description: String,
    val keywords: List<String> = listOf(),
)
