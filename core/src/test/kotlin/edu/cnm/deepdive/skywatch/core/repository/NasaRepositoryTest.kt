package edu.cnm.deepdive.skywatch.core.repository

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NasaRepositoryTest {

    @Test
    fun search() {
        val results = NasaRepository.search("curiosity")
        println(results)
    }

}