package com.scherule.scheduling.behavioral.steps

import com.scherule.scheduling.converters.DurationConverter
import com.scherule.scheduling.converters.IntervalConverter
import com.scherule.scheduling.helpers.SchedulingProblemPojo
import com.scherule.scheduling.helpers.SchedulingProblemPojo.Companion.aSchedulingProblem
import cucumber.api.Transform
import cucumber.api.java.en.Given
import cucumber.api.java.en.When
import cucumber.runtime.java.guice.ScenarioScoped
import org.joda.time.Duration
import org.joda.time.Interval
import org.joda.time.Period

@ScenarioScoped
internal class SchedulingSteps {

    var problemBuilder: SchedulingProblemPojo.Builder = aSchedulingProblem()

    @Given("there is a meeting scheduling problem")
    fun givenThereIsMeetingSchedulingProblem() {
        problemBuilder = aSchedulingProblem()
    }

    @Given("this meeting has minimum participants count of '(.*)'")
    fun givenThisMeetingHasMinimumParticipantsCountOf(minParticipants: String) {
        problemBuilder.withMinParticipants(minParticipants.toInt())
    }

    @Given("this meeting has minimum duration of '(.*)' hours")
    fun givenThisMeetingHasMinimumDurationOfHours(
            @Transform(DurationConverter::class) minLength: Duration
    ) {
        problemBuilder.longerThan(minLength)
    }

    @Given("this meeting has to happen in period '(.*)'")
    fun givenThisMeetingHasToHappenInPeriod(
            @Transform(IntervalConverter::class) interval: Interval
    ) {
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