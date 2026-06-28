package com.bikeleasing.web.tasks

import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.questions.page.TheWebPage
import net.serenitybdd.screenplay.waits.Wait
import org.hamcrest.CoreMatchers.containsString

object WaitFor {
    fun pageUrlContaining(expectedUrl: String): Performable =
        Task.where(
            "Wait for the page URL to contain $expectedUrl",
            Wait.until(TheWebPage.currentUrl(), containsString(expectedUrl))
        )
}