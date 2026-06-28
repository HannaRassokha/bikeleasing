package com.bikeleasing.web.tasks


import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.ensure.Ensure

object Verify {

    fun currentPageUrl(expectedUrl: String): Performable =
        Ensure.thatTheCurrentPage().currentUrl().contains(expectedUrl)
}
