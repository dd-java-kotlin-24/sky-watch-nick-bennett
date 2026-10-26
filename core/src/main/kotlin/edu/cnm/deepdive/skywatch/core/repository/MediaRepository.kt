package edu.cnm.deepdive.skywatch.core.repository

import edu.cnm.deepdive.skywatch.core.model.SearchResultImage
import java.time.OffsetDateTime

interface MediaRepository {

    /**
     * Submits a search request to a media search provider, returning the results as a
     * [List][List<SearchResultImage>]&mdash;that is, as a list of image references. The order of
     * the results returned by this function is left to the implementation; higher-level ordering
     * requirements should be addressed by consumers (direct or indirect) of this function.
     *
     * At least one of the filter parameter values ([searchFilter],
     * [startDate], [endDate]) must be provided, or [IllegalArgumentException] will be thrown.
     * If a non-`null` value is not provided for either [startDate] or [endDate], and [searchFilter]
     * is non-`null` but [searchFilter] is blank, [IllegalArgumentException] is thrown.
     * [IllegalArgumentException] is also thrown when non-`null` values are provided for both
     * [startDate] and [endDate], but `startDate > endDate`.
     *
     *
     * One implication of the above is that a date-time range may be specified by a single endpoint.
     * For example, a `null` [startDate], combined with a non-`null` [endDate] will return images
     * for _any_ date-time less than or equal to [endDate].
     *
     * If both a date range (one- or two-sided) and a non-`null`, non-empty [searchFilter] are
     * specified, the two criteria are combined with a logical AND.
     *
     * Paging is not supported by this API; the maximum size of the returned list&mdash;as well as
     * any truncation behavior&mdash;is provider- or implementation-specified.
     *
     * @param searchFilter optional search string.
     * @param startDate inclusive start of optional date range filter.
     * @param endDate inclusive end of optional date range filter.
     * @return [List][List<SearchResultImage>] of search results.
     *
     * @throws IllegalArgumentException If:
     *
     * - all of [searchFilter], [startDate], and [endDate] are `null`.
     * - [startDate], and [endDate] are `null`, and [searchFilter] is blank.
     * - non-`null` values are provided for both [startDate] and [endDate], but
     * `startDate > endDate` (using [OffsetDateTime.compareTo]).
     *
     * @throws Exception (with specific type specified by implementation) for any other
     * network-access or service communication failure.
     */
    fun search(
        searchFilter: String? = null,
        startDate: OffsetDateTime? = null,
        endDate: OffsetDateTime? = null,
    ): List<SearchResultImage>

}