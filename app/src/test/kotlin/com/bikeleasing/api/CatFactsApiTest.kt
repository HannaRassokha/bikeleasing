package com.bikeleasing.api

import com.bikeleasing.api.steps.CatFactsSteps
import com.bikeleasing.support.BaseTest
import net.serenitybdd.annotations.Steps
import net.serenitybdd.junit5.SerenityJUnit5Extension
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.Test

@ExtendWith(SerenityJUnit5Extension::class)
class CatFactsApiTest : BaseTest() {

    @Steps
    lateinit var apiClient: CatFactsSteps

    @Test
    @DisplayName("Fetch and validate cat facts from API")
    fun cats_facts() {
        val catFacts = apiClient.fetchCatFacts()
        apiClient.validateCatFacts(catFacts)
        apiClient.assertFactLength(catFacts)
    }
}
