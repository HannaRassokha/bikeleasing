package com.bikeleasing.steps

import com.bikeleasing.ui.CurrentPage
import com.bikeleasing.ui.HomePage
import net.thucydides.core.annotations.Step
import net.thucydides.core.steps.ScenarioSteps
import org.junit.jupiter.api.Assertions.assertEquals


open class Web : ScenarioSteps() {

    lateinit var homePage: HomePage
    lateinit var currentPage: CurrentPage

    @Step("Open URL: ")
    open fun navigateTo() {
        homePage.open()
    }

    @Step("Check current Url")
    open fun checkUrl() {
        assertEquals(
            "https://bikeleasing.de",
            currentPage.driver.currentUrl.removeSuffix("/"),
            "The current URL should match the expected one"
        )
    }
}