package edu.cnm.deepdive.skywatch.core.model

import com.squareup.moshi.JsonClass
import java.net.URL

@JsonClass(generateAdapter = true)
data class SearchResultImage(
    val description: String,
    val keywords: List<String>,
    val image: URL,
    val thumbnail: URL,
)
