package com.reltio.robinhoodhackaton.service;

import com.reltio.robinhoodhackaton.controller.dto.JobTriggerRequest;
import com.reltio.robinhoodhackaton.controller.dto.JobTriggerResponse;
import com.reltio.robinhoodhackaton.controller.dto.LoadIntensity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobTriggerService {

    private static final Logger LOGGER = LogManager.getLogger(JobTriggerService.class);

    @Value("${job.thread.load.threads}")
    private int loadNumberOfThreads;

    @Value("${job.thread.load.cycles}")
    private int loadNumberOfCycles;

    public JobTriggerResponse trigger(JobTriggerRequest request) {
        LOGGER.info("Request: {}", request);

        int loadIntensityFactor = getLoadIntensityFactor(request.loadIntensity());
        int numberOfThreads = loadNumberOfThreads * loadIntensityFactor;
        int numberOfCycles = loadNumberOfCycles * loadIntensityFactor;

        List<JobThread> jobThreads = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            JobThread thread = new JobThread(numberOfCycles, i);
            jobThreads.add(thread);
        }

        List<String> jobThreadNames = new ArrayList<>();
        for (JobThread jobThread : jobThreads) {
            jobThread.start();
            jobThreadNames.add(jobThread.getName());
        }

        return new JobTriggerResponse(true, jobThreadNames);
    }

    private static int getLoadIntensityFactor(LoadIntensity loadIntensity) {
        return switch (loadIntensity) {
            case LOW -> 1;
            case MEDIUM -> 2;
            case HIGH -> 3;
            case SUPER_HIGH -> 4;
        };
    }
}
