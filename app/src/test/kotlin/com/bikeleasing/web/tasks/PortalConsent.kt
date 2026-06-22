package com.bikeleasing.web.tasks

import com.bikeleasing.web.Language
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.SilentTask
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.actions.Browser
import net.serenitybdd.screenplay.actions.Evaluate

object PortalConsent {

    fun apply(language: Language = Language.DE): Task = SilentTask.where(
        setCookies(),
        setLanguage(language),
        Browser.refreshPage()
    )

    private fun setCookies(
        banner: Boolean = false,
        maps: Boolean = true,
        gtm: Boolean = true,
    ): Task {
        val item = "store-cookie-consent--settings"
        val value =
            "__q_objt|{\"showBanner\":$banner,\"cookies\":{\"googleMaps\":$maps,\"gtm\":$gtm}}"
        return SilentTask.where(setItem(item, value))
    }

    private fun setLanguage(language: Language): Performable =
        SilentTask.where(setItem("store-language--language", "__q_strn|${language.name.lowercase()}"))

    private fun setItem(item: String, value: String): Performable = SilentTask.where(
        Evaluate.javascript("window.localStorage.setItem(arguments[0],arguments[1])", item, value)
    )
}
