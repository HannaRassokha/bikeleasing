package com.bikeleasing.web.pages

import net.serenitybdd.core.pages.PageObject
import net.serenitybdd.screenplay.targets.Target

object Portal : PageObject() {
    val REGISTER_LINK: Target = Target.the("'Jetzt registrieren' link")
        .locatedBy(".register-link")
}