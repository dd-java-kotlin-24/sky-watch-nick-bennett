package edu.cnm.deepdive.skywatch.core.service

import edu.cnm.deepdive.skywatch.core.model.dto.SearchResult
import retrofit2.Call

interface NasaMediaApi {

    fun search(
        query: String? = null,
        mediaType: String = "image",
        startYear: Int? = null,
        endYear: Int? = null,
    ): Call<SearchResult>

}