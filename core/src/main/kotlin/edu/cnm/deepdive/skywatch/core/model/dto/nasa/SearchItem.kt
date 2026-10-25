package edu.cnm.deepdive.skywatch.core.model.dto.nasa

import java.net.URL

data class SearchItem(
    val mediaCollectionUrl: URL,
    val data: List<ItemData>,
    val links: List<Link>,
)
