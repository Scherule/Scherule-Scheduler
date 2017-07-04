package com.scherule.scheduling.behavioral.steps

import cucumber.api.java.en.Given
import cucumber.runtime.java.guice.ScenarioScoped

@ScenarioScoped
class SchedulingSteps {

    @Given("there is x")
    fun thereIsX() {
        System.out.println("x")
    }

}