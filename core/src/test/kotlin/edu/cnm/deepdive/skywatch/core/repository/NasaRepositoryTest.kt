package edu.cnm.deepdive.skywatch.core.repository

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.OffsetDateTime

class NasaRepositoryTest {

    @Test
    fun search() {
        val searchResults = NasaRepository.search(searchFilter = "Sputnik", startDate = OffsetDateTime.parse("2020-01-01T00:00:00.000Z"))
        println(searchResults)
    }

}