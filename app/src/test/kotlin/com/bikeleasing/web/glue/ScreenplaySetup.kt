package com.bikeleasing.web.glue

import io.cucumber.java.Before
import io.cucumber.java.ParameterType
import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.actors.OnStage
import net.serenitybdd.screenplay.actors.OnlineCast

class ScreenplaySetup {

    @Before
    fun setTheStage() {
        OnStage.setTheStage(OnlineCast())
    }

    @ParameterType("he|she|they|[A-Z][a-zA-Z]+")
    fun actor(name: String): Actor =
        if (name.first().isUpperCase()) OnStage.theActorCalled(name)
        else OnStage.theActorInTheSpotlight()

    @ParameterType("Bikeleasing|Portal|Registration")
    fun page(name: String): String = name
}