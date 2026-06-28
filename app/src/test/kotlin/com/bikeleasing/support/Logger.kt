package com.bikeleasing.support

import net.serenitybdd.core.di.SerenityInfrastructure
import mu.KotlinLogging
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration

object Logger {
    val LOGGER = KotlinLogging.logger{}
    private val envConfig: EnvironmentSpecificConfiguration =
        EnvironmentSpecificConfiguration.from(SerenityInfrastructure.getEnvironmentVariables())
}