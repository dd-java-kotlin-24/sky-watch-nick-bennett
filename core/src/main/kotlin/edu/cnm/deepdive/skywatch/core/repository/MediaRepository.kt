package edu.cnm.deepdive.skywatch.core.repository

import edu.cnm.deepdive.skywatch.core.model.domain.SearchResultImage
import java.time.OffsetDateTime

interface
MediaRepository {

    /**
     * Submits a search request to a media service, returning the results as a
     * [List][List<SearchResultImage>]. The order of the results returned by this function is left
     * to the implementation; higher-level ordering requirements should be addressed by consumers
     * (direct or indirect) of the results returned by this function.
     *
     * At least one of the filter parameter values ([searchFilter],
     * [startDate], [endDate]) must be provided, or [IllegalArgumentException] will be thrown.
     *
     * @param searchFilter optional search string.
     * @param startDate start of optional date range filter, expressed as an ISO 8601 offset date-time
     * (`YYYY-MM-DDTHH:mm[:ss[.SSS]±HH[:]mm`).
     * @param endDate end of optional date range filter, expressed as an ISO 8601 offset date-time
     * (`YYYY-MM-DDTHH:mm[:ss[.SSS]±HH[:]mm`).
     * @throws IllegalArgumentException If at least one of [searchFilter], [startDate], and
     * [endDate] is not `null`.
     */
    fun search(
        searchFilter: String? = null,
        startDate: OffsetDateTime? = null,
        endDate: OffsetDateTime? = null,
    ): List<SearchResultImage>

    companion object {

        val instance: MediaRepository
            get() = NasaRepository

    }
}