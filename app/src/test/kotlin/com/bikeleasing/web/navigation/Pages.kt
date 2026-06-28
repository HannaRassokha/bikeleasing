package com.bikeleasing.web.navigation

object Pages {

    fun navigationUrl(page: String): String = when (page) {
        "Bikeleasing" -> "https://bikeleasing.de"
        else -> error("No navigation URL defined for page '$page'")
    }

    fun expectedUrl(page: String): String = when (page) {
        "Bikeleasing" -> "bikeleasing.de"
        "Portal" -> "portal.bikeleasing.de/login"
        "Registration" -> "portal.bikeleasing.de/registration"
        else -> error("No expected URL defined for page '$page'")
    }
}
