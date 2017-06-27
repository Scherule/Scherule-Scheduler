package com.scherule.scheduling;

import com.scherule.commons.MicroServiceLauncher;

class SchedulingApplication extends MicroServiceLauncher {

    public static final SchedulingApplicationContext context = new SchedulingApplicationContext();

    public static void main(String[] args) {
        new SchedulingApplication().dispatch(args);
    }

}