package edu.cnm.deepdive.skywatch.core.model.dto.nasa

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.net.URL

@JsonClass(generateAdapter = true)
data class Link(
    @Json(name = "href") val url: URL,
    @Json(name = "rel") val role: String, // Should this be an enum?
    val width: Int?,
    val height: Int?,
    val size: Int?,
)
