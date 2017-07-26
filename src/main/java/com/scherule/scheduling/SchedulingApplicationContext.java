package com.scherule.scheduling;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.scherule.scheduling.modules.MappingModule;
import com.scherule.scheduling.modules.QueueModule;
import com.scherule.scheduling.modules.SchedulersModule;

class SchedulingApplicationContext {

    private final Injector injector = Guice.createInjector(
            new QueueModule(),
            new MappingModule(),
            new SchedulersModule()
    );

    Injector getInjector() {
        return injector;
    }

}
