package com.scherule.scheduling

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.Module
import com.scherule.scheduling.modules.MappingModule
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback
import org.junit.jupiter.api.extension.ExtensionContext

class InjectorExtension(
        modules: List<Module> = listOf(MappingModule())
) : BeforeTestExecutionCallback {

    val injector: Injector = Guice.createInjector(modules)

    override fun beforeTestExecution(context: ExtensionContext?) {
        injector.injectMembers(context?.testInstance)
    }

}