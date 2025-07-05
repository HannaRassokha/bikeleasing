package com.bikeleasing.test

import com.bikeleasing.base.BaseTest
import com.bikeleasing.steps.Api
import net.serenitybdd.junit5.SerenityJUnit5Extension
import net.thucydides.core.annotations.Steps
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.Test

@ExtendWith(SerenityJUnit5Extension::class)
open class ApiTest : BaseTest() {

    @Steps
    lateinit var apiClient: Api

    @Test
    fun cats_facts() {
        val catFacts = apiClient.fetchCatFacts()
        apiClient.validateCatFacts(catFacts)
        apiClient.assertFactLength(catFacts)
    }
}