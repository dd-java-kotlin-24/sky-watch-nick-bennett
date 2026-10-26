package edu.cnm.deepdive.skywatch.core.api

import edu.cnm.deepdive.skywatch.core.model.dto.nasa.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaMediaApi {

    @GET("search")
    fun search(
        @Query("q") query: String? = null,
        @Query("media_type") mediaType: String = "image",
        @Query("year_start") startYear: Int? = null,
        @Query("year_end") endYear: Int? = null,
    ): Call<SearchResult>

}