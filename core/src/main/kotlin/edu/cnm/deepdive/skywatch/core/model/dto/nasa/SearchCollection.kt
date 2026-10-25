package edu.cnm.deepdive.skywatch.core.model.dto.nasa

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchCollection(
    val items: List<SearchItem>,
)