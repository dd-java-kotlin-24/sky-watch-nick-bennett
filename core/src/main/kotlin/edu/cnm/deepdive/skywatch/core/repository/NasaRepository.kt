package edu.cnm.deepdive.skywatch.core.repository

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import edu.cnm.deepdive.skywatch.core.api.NasaMediaApi
import edu.cnm.deepdive.skywatch.core.model.domain.SearchResultImage
import edu.cnm.deepdive.skywatch.core.model.dto.nasa.SearchItem
import edu.cnm.deepdive.skywatch.core.model.dto.nasa.SearchResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.InputStreamReader
import java.net.URI
import java.net.URL
import java.time.OffsetDateTime
import java.util.*

private const val LOCAL_PROPERTIES = "local.properties"
private const val CANONICAL_IDENTIFIER = "canonical"
private const val THUMBNAIL_IDENTIFIER = "preview"

/**
 * Implements [MediaRepository] for the NASA media API.
 */
internal object NasaRepository : MediaRepository {

    private val api: NasaMediaApi

    init {
        val properties = javaClass.classLoader
            .getResourceAsStream(LOCAL_PROPERTIES)
            ?.use { input ->
                Properties().apply {
                    load(InputStreamReader(input))
                }
            } ?: throw RuntimeException("$LOCAL_PROPERTIES not found on classpath")
        val baseUrl = properties["base_url"] as String
        val loggingLevel = properties["logging_level"] as String
        val moshi = Moshi.Builder()
            .add(OffsetDateTimeAdapter())
            .add(UrlAdapter())
            .build()
        val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.valueOf(loggingLevel.uppercase()))
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
        api = retrofit.create(NasaMediaApi::class.java)
    }

    /**
     * Submits a search request to the NASA media API, returning the results as a
     * [List][List<SearchResultImage>]. The order of the results returned by this function is that
     * dictated by the NASA API; higher-level ordering requirements should be addressed by consumers
     * (direct or indirect) of the results returned by this function.
     *
     * The NASA media search API supports specification of dates at the year level only; thus, only
     * the year portions of [startDate] and [endDate] (if provided) are used.
     *
     * At least one of the filter parameter values ([searchFilter],
     * [startDate], [endDate]) must be provided, or [IllegalArgumentException] will be thrown.
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
    override fun search(
        searchFilter: String?,
        startDate: OffsetDateTime?,
        endDate: OffsetDateTime?,
    ): List<SearchResultImage> {
        require(searchFilter != null || startDate != null || endDate != null)
        val response: Response<SearchResult> =
            api.search(query = searchFilter, startYear = startDate?.year, endYear = endDate?.year)
                .execute()
        return if (!response.isSuccessful) {
            throw RuntimeException(response.errorBody()?.string())
        } else if (response.body() == null) {
            throw RuntimeException("No content received")
        } else {
            response.body()!!
                .collection
                .items
                .map(::extractResult)
        }
    }

    private fun extractResult(item: SearchItem): SearchResultImage {
        val itemData = item.data[0]
        val description = itemData.description
        val keywords = itemData.keywords
        val links = item.links
        val canonical = links
            .firstOrNull() { it.role == CANONICAL_IDENTIFIER }
            ?: links.maxBy { it.width ?: 0 }
        val thumbnail = links
            .firstOrNull() { it.role == THUMBNAIL_IDENTIFIER }
            ?: links.minBy { it.width ?: Int.MAX_VALUE }
        return SearchResultImage(description, keywords, canonical.url, thumbnail.url)
    }

    private class OffsetDateTimeAdapter {

        @FromJson
        fun fromJson(value: String): OffsetDateTime? =
            OffsetDateTime.parse(value)

        @ToJson
        fun toJson(value: OffsetDateTime?) =
            value?.toString()
    }

    private class UrlAdapter {

        @FromJson
        fun fromJson(value: String): URL? =
            URI(value).toURL()

        @ToJson
        fun toJson(value: URL?): String? =
            value?.toString()

    }

}
