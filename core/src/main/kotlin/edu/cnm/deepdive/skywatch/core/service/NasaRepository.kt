package edu.cnm.deepdive.skywatch.core.service

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.addAdapter
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
    ): List<SearchResultImage> {
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
