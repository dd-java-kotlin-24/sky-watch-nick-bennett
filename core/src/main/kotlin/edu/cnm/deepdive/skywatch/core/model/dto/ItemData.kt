package edu.cnm.deepdive.skywatch.core.model.dto

import com.squareup.moshi.Json
import java.time.OffsetDateTime

data class ItemData(
    val center: String,
    @Json(name = "date_created") val dateCreated: OffsetDateTime,
    val description: String,
    val keywords: Array<String>, // TODO: Consider overriding equals and hashCode.
)
