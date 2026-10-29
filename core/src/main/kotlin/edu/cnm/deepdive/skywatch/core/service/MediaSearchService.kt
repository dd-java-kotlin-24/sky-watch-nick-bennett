package edu.cnm.deepdive.skywatch.core.service

import edu.cnm.deepdive.skywatch.core.model.SearchResultImage
import java.time.OffsetDateTime
import java.util.concurrent.CompletableFuture

interface MediaSearchService {

    fun search(
        searchFilter: String? = null,
        startDate: OffsetDateTime? = null,
        endDate: OffsetDateTime? = null,
    ): CompletableFuture<List<SearchResultImage>>

}