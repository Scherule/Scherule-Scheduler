package com.scherule.scheduling

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith


@RunWith(Cucumber::class)
@CucumberOptions(
        features = arrayOf("src/test/resources/features"),
        format = arrayOf("pretty", "html:target/cucumber")
)
class CucumberTestRunner