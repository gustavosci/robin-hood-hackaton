package com.reltio.robinhoodhackaton.controller.dto;

import java.util.List;

public record JobTriggerResponse(boolean success, List<String> threadNames) { }
