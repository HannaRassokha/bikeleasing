package com.bikeleasing.base


import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration
import net.thucydides.core.environment.SystemEnvironmentVariables
import net.thucydides.core.util.EnvironmentVariables
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class BaseTest {

    protected lateinit var logger: Logger
    protected lateinit var environmentVariables: EnvironmentVariables
    protected lateinit var environmentName: String
    protected lateinit var webBaseUrl: String
    open lateinit var factsUri: String

    @BeforeAll
    fun setupEnvironment() {
        logger = LoggerFactory.getLogger(this::class.java)

        environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables()
        environmentName = environmentVariables.getProperty("environment") ?: "default"

        logger.info("Test Environment: $environmentName")

        webBaseUrl = EnvironmentSpecificConfiguration
            .from(environmentVariables)
            .getProperty("webdriver.base.url")
            ?: error("Missing 'webdriver.base.url' in environment config")

        factsUri = EnvironmentSpecificConfiguration
            .from(environmentVariables)
            .getProperty("api.facts.uri") ?: ""

        logger.info("Web Base URL: $webBaseUrl")
        if (factsUri.isNotEmpty()) logger.info("Facts API URI: $factsUri")
    }

    @BeforeEach
    fun beforeEach(testInfo: TestInfo) {
        logger.info("Starting test: ${testInfo.displayName}")
    }

    @AfterEach
    fun afterEach(testInfo: TestInfo) {
        logger.info("Finished test: ${testInfo.displayName}")
    }

}