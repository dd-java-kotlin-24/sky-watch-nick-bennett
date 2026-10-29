package edu.cnm.deepdive.skywatch.core.service

import edu.cnm.deepdive.skywatch.core.model.SearchResultImage
import edu.cnm.deepdive.skywatch.core.repository.MediaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.future.future
import java.time.OffsetDateTime
import java.util.concurrent.CompletableFuture

internal class MediaSearchServiceImpl(private val repository: MediaRepository) :
    MediaSearchService {

    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun search(
        searchFilter: String?,
        startDate: OffsetDateTime?,
        endDate: OffsetDateTime?
    ): CompletableFuture<List<SearchResultImage>> =
        scope.future {
            repository.search(searchFilter, startDate, endDate)
        }

}