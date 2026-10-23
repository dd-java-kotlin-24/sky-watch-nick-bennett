package edu.cnm.deepdive.skywatch.core.service

import edu.cnm.deepdive.skywatch.core.model.dto.SearchItem
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
    ): Array<SearchItem>

}