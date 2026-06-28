package com.bikeleasing.web.tasks

import com.bikeleasing.web.pages.Bikeleasing
import com.bikeleasing.web.pages.Portal
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.actions.Click
import net.serenitybdd.screenplay.actions.Switch

object Select {

    fun option(page: String): Performable = when (page) {
        "Portal" -> Task.where(
            "{0} selects the Portal option",
            Click.on(Bikeleasing.PORTAL_LINK),
            Switch.toNewWindow(),
            PortalConsent.apply()
        )

        "Registration" -> Task.where(
            "{0} selects the Registration option",
            Click.on(Portal.REGISTER_LINK)
        )

        else -> error("No selectable option defined for page '$page'")
    }
}
