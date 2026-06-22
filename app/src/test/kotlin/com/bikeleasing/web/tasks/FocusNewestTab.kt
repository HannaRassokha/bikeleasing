package com.bikeleasing.web.tasks

import net.serenitybdd.annotations.Step
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Interaction
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.abilities.BrowseTheWeb

open class FocusNewestTab : Interaction {

    @Step("{0} switches to the newest browser tab")
    override fun <T : Actor> performAs(actor: T) {
        val driver = BrowseTheWeb.`as`(actor).driver
        driver.switchTo().window(driver.windowHandles.last())
    }

    companion object {
        fun now(): Performable = FocusNewestTab()
    }
}