package com.bikeleasing.web.tasks

import com.bikeleasing.support.Session
import net.serenitybdd.core.Serenity.sessionVariableCalled
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Interaction
import net.serenitybdd.screenplay.Performable
import net.serenitybdd.screenplay.SilentTask
import net.serenitybdd.screenplay.Task
import net.serenitybdd.screenplay.abilities.BrowseTheWeb
import net.serenitybdd.screenplay.actions.Browser
import org.openqa.selenium.Cookie
import java.net.URLEncoder
import java.time.Instant
import java.util.function.Consumer

object BikeleasingConsent {

    private const val CONSENT_COOKIE = "cookiefirst-consent"
    private const val POLICY_VERSION = "439a4ffe-d608-43c1-8c48-af2818e32946"

    private const val LANGUAGE_COOKIE = "lang"
    private const val DEFAULT_LANGUAGE = "de-DE"

    fun setAcceptCookiesAndLanguage(): Task = SilentTask.where(
        setCookies(),
        setLanguage(sessionVariableCalled<String>(Session.LANGUAGE) ?: DEFAULT_LANGUAGE),
        Browser.refreshPage()
    )

    private fun setCookies(): Performable =
        Interaction.where<Performable>("{0} accepts all cookies", Consumer { actor: Actor ->
            val consent =
                """{"necessary":true,"performance":true,"functional":true,"advertising":true,"timestamp":"${Instant.now()}","type":"category","version":"$POLICY_VERSION"}"""
            actor.addCookie(CONSENT_COOKIE, URLEncoder.encode(consent, "UTF-8"))
        })

    private fun setLanguage(language: String): Performable =
        Interaction.where<Performable>("{0} sets the language to $language", Consumer { actor: Actor ->
            actor.addCookie(LANGUAGE_COOKIE, language)
        })

    private fun Actor.addCookie(name: String, value: String) {
        BrowseTheWeb.`as`(this).driver.manage().addCookie(
            Cookie.Builder(name, value)
                .domain(".bikeleasing.de")
                .path("/")
                .isSecure(true)
                .build()
        )
    }
}
