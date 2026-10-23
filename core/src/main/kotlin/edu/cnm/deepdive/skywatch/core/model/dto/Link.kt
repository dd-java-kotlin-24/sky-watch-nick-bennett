package edu.cnm.deepdive.skywatch.core.model.dto

import com.squareup.moshi.Json
import java.net.URL

data class Link(
    @Json(name = "href") val url: URL,
    @Json(name = "rel") val role: String, // Should this be an enum?
    val width: Int,
    val height: Int,
    val size: Int,
)
