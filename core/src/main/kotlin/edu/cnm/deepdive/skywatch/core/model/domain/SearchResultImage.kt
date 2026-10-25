package edu.cnm.deepdive.skywatch.core.model.domain

import java.net.URL

data class SearchResultImage(
    val description: String,
    val keywords: List<String>,
    val image: URL,
    val thumbnail: URL,
)
