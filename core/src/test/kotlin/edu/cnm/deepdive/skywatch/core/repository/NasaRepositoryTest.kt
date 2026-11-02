package edu.cnm.deepdive.skywatch.core.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import java.time.OffsetDateTime

class NasaRepositoryTest {

    val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Test
    fun search() {
        scope.run {
            val searchResults = NasaRepository.search(
                searchFilter = "Sputnik",
                startDate = OffsetDateTime.parse("2020-01-01T00:00:00.000Z")
            )
            println(searchResults)
        }
    }

    @ParameterizedTest
    @CsvFileSource(
        resources = ["search-invalid.csv"],
        useHeadersInDisplayName = true,
        nullValues = ["null"]
    )
    fun `search throws exception on invalid arguments`(
        searchFilter: String?,
        startDate: OffsetDateTime?,
        endDate: OffsetDateTime?,
        expectedException: Class<out Throwable>
    ) {

    }

}