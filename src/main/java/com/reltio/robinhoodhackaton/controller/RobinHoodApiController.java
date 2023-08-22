package com.reltio.robinhoodhackaton.controller;

import com.reltio.robinhoodhackaton.controller.dto.JobTriggerRequest;
import com.reltio.robinhoodhackaton.controller.dto.JobTriggerResponse;
import com.reltio.robinhoodhackaton.service.JobTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/robin-hood")
public class RobinHoodApiController {

    @Autowired
    private JobTriggerService jobTriggerService;

    @PostMapping
    public JobTriggerResponse triggerJob(@RequestBody JobTriggerRequest request) {
        boolean success = jobTriggerService.trigger(request);
        return new JobTriggerResponse(success);
    }
}
