package com.reltio.robinhoodhackaton.service;

import com.reltio.robinhoodhackaton.controller.dto.JobTriggerRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class JobTriggerService {

    private static final Logger LOGGER = LogManager.getLogger(JobTriggerService.class);

    public boolean trigger(JobTriggerRequest request) {
        LOGGER.info("Request: {}", request);
        return true;
    }
}
