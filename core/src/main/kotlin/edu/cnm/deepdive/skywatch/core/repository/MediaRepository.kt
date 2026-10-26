package edu.cnm.deepdive.skywatch.core.repository

import edu.cnm.deepdive.skywatch.core.model.domain.SearchResultImage
import java.time.OffsetDateTime

/**
 * Declares functionality of a searchable media repository. Currently, images are the only
 * supported media, and only read operations are declared.
 */
interface
MediaRepository {

    /**
     * Submits a search request to a media service, returning the results as a
     * [List][List<SearchResultImage>]. The order of the results returned by this function is left
     * to the implementation; higher-level ordering requirements should be addressed by consumers
     * (direct or indirect) of the results returned by this function.
     *
     * At least one of the filter parameter values ([searchFilter],
     * [startDate], [endDate]) must be provided, or [IllegalArgumentException] will be thrown. The
     * same exception will be thrown if non-`null` values are provided for both [startDate] and
     * [endDate], but (`startDate > endDate`).
     *
     * @param searchFilter optional search string.
     * @param startDate start of optional date range filter, expressed as an ISO 8601 offset date-time
     * (`YYYY-MM-DDTHH:mm[:ss[.SSS]±HH[:]mm`).
     * @param endDate end of optional date range filter, expressed as an ISO 8601 offset date-time
     * (`YYYY-MM-DDTHH:mm[:ss[.SSS]±HH[:]mm`).
     * @throws IllegalArgumentException If all of [searchFilter], [startDate], and [endDate] are
     * `null`, or if non-`null` values are provided for both [startDate] and [endDate], but
     * `startDate > endDate`.
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