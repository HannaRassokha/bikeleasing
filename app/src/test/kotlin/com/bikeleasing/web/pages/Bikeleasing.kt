package com.bikeleasing.web.pages

import net.serenitybdd.core.pages.PageObject
import net.serenitybdd.screenplay.targets.Target

object Bikeleasing : PageObject() {
    val PORTAL_LINK: Target = Target.the("Portal link")
        .locatedBy("a[href*='portal.bikeleasing.de/login']")
}