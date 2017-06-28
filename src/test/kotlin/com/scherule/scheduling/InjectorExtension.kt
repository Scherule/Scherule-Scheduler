package com.scherule.scheduling

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Module
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback
import org.junit.jupiter.api.extension.TestExtensionContext

class InjectorExtension(
        modules: List<Module> = listOf(SchedulingApplicationContext())
) : BeforeTestExecutionCallback {

    val injector: Injector = Guice.createInjector(modules)

    override fun beforeTestExecution(context: TestExtensionContext?) {
        injector.injectMembers(context?.testInstance)
    }

}