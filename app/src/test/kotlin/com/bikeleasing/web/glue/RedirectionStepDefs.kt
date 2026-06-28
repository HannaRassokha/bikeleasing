package com.bikeleasing.web.glue

import com.bikeleasing.web.navigation.Pages
import com.bikeleasing.web.tasks.BikeleasingConsent
import com.bikeleasing.web.tasks.Select
import com.bikeleasing.web.tasks.Verify
import com.bikeleasing.web.tasks.WaitFor
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.actions.Open

class RedirectionStepDefs {

    @Given("{actor} opens {page} url")
    fun openPageUrl(actor: Actor, page: String) =
        actor.attemptsTo(
            Open.url(Pages.navigationUrl(page)),
            BikeleasingConsent.setAcceptCookiesAndLanguage()
        )

    @And("{actor} selects {page} option")
    fun selectOption(actor: Actor, page: String) =
        actor.attemptsTo(Select.option(page))

    @Then("{actor} should be on {page} page")
    fun verifyLandingPage(actor: Actor, page: String) {
        actor.attemptsTo(
            WaitFor.pageUrlContaining(Pages.expectedUrl(page)),
            Verify.currentPageUrl(Pages.expectedUrl(page))
        )
    }
}
