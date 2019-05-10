package com.robertx22.uncommon.testing;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

public class Watch {

    StopWatch stop = new StopWatch();
    int min = 0;
    TimeUnit unit = TimeUnit.MICROSECONDS;

    public Watch() {
        stop = new StopWatch();
        stop.start();
    }

    public Watch(TimeUnit unit, int min) {

        this.min = min;
        this.unit = unit;

        stop = new StopWatch();
        stop.start();

    }

    public void print() {

        stop.stop();
        Long time = stop.getTime(unit);
        if (time > min) {
            System.out.println("Action took: " + time + " " + unit.name().toLowerCase());

        }
    }

}