package edu.cnm.deepdive.skywatch.core.service

import edu.cnm.deepdive.skywatch.core.model.domain.SearchResultImage
import java.time.OffsetDateTime

interface MediaRepository {

    /**
     * TODO Add contract description.
     *
     * @param searchFilter
     * @param startDate
     * @param endDate
     */
    fun search(
        searchFilter: String? = null,
        startDate: OffsetDateTime? = null,
        endDate: OffsetDateTime? = null,
    ): List<SearchResultImage>

}