package com.bikeleasing.steps


import CatFact
import CatFactResponse
import io.restassured.RestAssured
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration
import net.thucydides.core.annotations.Step
import net.thucydides.core.environment.SystemEnvironmentVariables
import org.junit.jupiter.api.Assertions.assertEquals


open class Api {
    private val environmentVariables by lazy { SystemEnvironmentVariables.createEnvironmentVariables() }

    private val factsUri = EnvironmentSpecificConfiguration
        .from(environmentVariables)
        .getProperty("api.facts.uri") ?: ""

    private val apiBaseUrl: String
        get() = EnvironmentSpecificConfiguration
            .from(environmentVariables)
            .getProperty("api.base.url")
            ?: error("Missing 'api.base.url' in environment config")

    @Step("Fetch cat facts from endpoint")
    fun fetchCatFacts(): List<CatFact> {
        val catFactResponse: CatFactResponse = RestAssured
            .given()
            .baseUri(apiBaseUrl)
            .`when`()
            .get(factsUri)
            .then()
            .statusCode(200)
            .extract()
            .`as`(CatFactResponse::class.java).also {
                println("Response body: $it")
            }
        return catFactResponse.data
    }

    @Step("Validate cat facts")
    fun validateCatFacts(facts: List<CatFact>) {
        println("5 Cat Facts:")
        facts.take(5).forEachIndexed { i, fact ->
            println("${i + 1}. ${fact.fact}")
        }
    }

    @Step("Assert first cat fact length")
    fun assertFactLength(facts: List<CatFact>) {
        val firstFact = facts.first()
        println("Fact: ${firstFact.fact}")
        println("Expected length: ${firstFact.length}, Actual: ${firstFact.fact.length}")
        assertEquals(firstFact.length, firstFact.fact.length, "Length of the first fact should match 'length' field")
    }
}