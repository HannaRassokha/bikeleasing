package com.bikeleasing.test

import com.bikeleasing.base.BaseTest
import com.bikeleasing.steps.Web
import net.serenitybdd.junit5.SerenityJUnit5Extension
import net.thucydides.core.annotations.Managed
import net.thucydides.core.annotations.Steps

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.openqa.selenium.WebDriver

@ExtendWith(SerenityJUnit5Extension::class)
open class WebTest : BaseTest() {

    @Steps
    lateinit var webClient: Web

    @Managed(driver = "chrome")
    lateinit var browser: WebDriver

    @Test
    fun `bikeleasing serenity test`() {
        webClient.navigateTo()
        webClient.checkUrl()
    }
}
