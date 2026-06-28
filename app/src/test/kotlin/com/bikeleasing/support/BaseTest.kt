package com.bikeleasing.support

import com.bikeleasing.support.Logger.LOGGER
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.TestInstance
import kotlin.jvm.optionals.getOrNull

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class BaseTest {

    @BeforeEach
    fun beforeEach(testInfo: TestInfo) {
        LOGGER.info {"Starting test: ${testInfo.testName()}"}
    }

    @AfterEach
    fun afterEach(testInfo: TestInfo) {
        LOGGER.info {"Finished test: ${testInfo.testName()}"}
    }

    private fun TestInfo.testName(): String =
        testClass.getOrNull()?.simpleName ?: displayName
}