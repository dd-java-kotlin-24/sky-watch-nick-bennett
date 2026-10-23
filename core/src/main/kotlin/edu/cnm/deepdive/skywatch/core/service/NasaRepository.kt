package edu.cnm.deepdive.skywatch.core.service

import edu.cnm.deepdive.skywatch.core.model.dto.SearchItem
import java.time.OffsetDateTime

internal object NasaRepository : MediaRepository {

    // TODO: Initialize Moshi, OkHTTP client with logging interceptor, and Retrofit; then use
    //   Retrofit instance to create an implementation (and a instance of it) of the NasaMediaApi
    //   interface.
    
    /**
     * TODO Add contract description.
     *
     * @param searchFilter
     * @param startDate
     * @param endDate
     */
    override fun search(
        searchFilter: String?,
        startDate: OffsetDateTime?,
        endDate: OffsetDateTime?,
    ): Array<SearchItem> {
        TODO("Not yet implemented")
    }
}