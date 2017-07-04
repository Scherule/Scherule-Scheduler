package com.scherule.scheduling.behavioral.steps

import cucumber.api.java.en.Given
import cucumber.api.java.en.When
import cucumber.runtime.java.guice.ScenarioScoped

@ScenarioScoped
class SchedulingSteps {

    @Given("there is a meeting scheduling problem")
    fun givenThereIsMeetingSchedulingProblem() {
        System.out.println("x")
    }

    @Given("this meeting has minimum participants count of '(\\d+)'")
    fun givenThisMeetingHasMinimumParticipantsCountOf() {
        System.out.println("x")
    }

    @Given("this meeting has minimum duration of '(\\d+)' hours")
    fun givenThisMeetingHasMinimumDurationOfHours() {
        System.out.println("x")
    }

    @Given("this meeting has to happen in period '(.*)'")
    fun givenThisMeetingHasToHappenInPeriod() {
        System.out.println("x")
    }

    @Given("there is a participant")
    fun givenThereIsParticipant() {
        System.out.println("x")
    }

    @Given("this participant declares availability '(.*)' with weight '(\\d+)'")
    fun givenThisParticipantDeclaresAvailabilityWithWeight() {
        System.out.println("x")
    }

    @When("the meeting was scheduled")
    fun whenTheMeetingWasScheduled() {
        System.out.println("x")
    }

    @When("the meeting scheduled is in period '(.*)'")
    fun thenTheMeetingScheduledIsInPeriod() {
        System.out.println("x")
    }

}