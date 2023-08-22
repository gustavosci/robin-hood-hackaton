package com.reltio.robinhoodhackaton.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobThread extends Thread {

    private static final Logger LOGGER = LogManager.getLogger(JobThread.class);

    private final long numberOfCycles;

    public JobThread(long numberOfCycles, int jobNumber) {
        super("job-" + numberOfCycles + "-" + jobNumber);
        this.numberOfCycles = numberOfCycles;
    }

    @Override
    public void run() {
        LOGGER.info("Starting thread {} - Number of Cycles: {}", getName(), numberOfCycles);
        long start = System.nanoTime();
        for (int i = 0; i < numberOfCycles; i++) {
            LOGGER.info("Thread {} - Cycle {}", getName(), i);
            spin(500);
        }
        LOGGER.info("Took {} to complete {}", ((System.nanoTime()-start) / 1000000), getName());
    }

    private void spin(int milliseconds) {
        long sleepTime = milliseconds * 1000000L; // convert to nanoseconds
        long startTime = System.nanoTime();
        while ((System.nanoTime() - startTime) < sleepTime) {
            // do nothing
        }
    }
}
