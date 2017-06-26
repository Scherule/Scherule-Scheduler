package com.scherule.scheduling;

import com.scherule.commons.MicroServiceLauncher;

class SchedulingApplication extends MicroServiceLauncher {

    public static void main(String[] args) {
        new SchedulingApplication().dispatch(args);
    }

}