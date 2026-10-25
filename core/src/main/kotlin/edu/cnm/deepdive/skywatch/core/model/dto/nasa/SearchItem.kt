package edu.cnm.deepdive.skywatch.core.model.dto.nasa

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.net.URL

@JsonClass(generateAdapter = true)
data class SearchItem(
    @Json(name = "href") val mediaCollectionUrl: URL,
    val data: List<ItemData>,
    val links: List<Link>,
)
