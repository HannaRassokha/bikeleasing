package com.bikeleasing.api.steps


import com.bikeleasing.api.model.CatFact
import com.bikeleasing.api.model.CatFactResponse
import com.bikeleasing.support.Logger.LOGGER
import net.serenitybdd.annotations.Step
import net.serenitybdd.core.Serenity
import net.serenitybdd.core.di.SerenityInfrastructure
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration
import net.serenitybdd.rest.SerenityRest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.htmlunit.http.HttpStatus


open class CatFactsSteps {
    private val environmentVariables by lazy { SerenityInfrastructure.getEnvironmentVariables() }

    private val factsUri = EnvironmentSpecificConfiguration
        .from(environmentVariables)
        .getProperty("api.facts.uri") ?: ""

    private val apiBaseUrl: String
        get() = EnvironmentSpecificConfiguration
            .from(environmentVariables)
            .getProperty("api.base.url")
            ?: error("Missing 'api.base.url' in environment config")

    @Step("Fetch cat facts from endpoint")
    open fun fetchCatFacts(): List<CatFact> {
        val catFactResponse: CatFactResponse = SerenityRest
            .given()
            .baseUri(apiBaseUrl)
            .queryParam("max_length",50)
            .queryParams("limit",5)
            .`when`()
            .get(factsUri)
            .then()
            .statusCode(HttpStatus.OK_200)
            .extract()
            .`as`(CatFactResponse::class.java)
            .also {
                LOGGER.info {"Response body: $it"}
            }
        return catFactResponse.data
    }

    @Step("Validate cat facts")
    open fun validateCatFacts(facts: List<CatFact>) {
        LOGGER.info {"5 Cat Facts:"}
        facts.take(5).forEachIndexed { i, fact ->
            LOGGER.info {"${i + 1}. ${fact.fact}"}
            Serenity.reportThat("Cat fact #${i + 1}: ${fact.fact}") { }
        }
    }

    @Step("Assert first cat fact length")
    open fun assertFactLength(facts: List<CatFact>) {
        val firstFact = facts.first()
        LOGGER.info{ "Fact: ${firstFact.fact}"}
        Serenity.reportThat("Fact: ${firstFact.fact}") { }
        LOGGER.info{ "Expected length: ${firstFact.length}, Actual: ${firstFact.fact.length}"}
        Serenity.reportThat("Expected length: ${firstFact.length}, Actual: ${firstFact.fact.length}") { }
        assertThat(firstFact.fact.length, equalTo(firstFact.length))
    }
}