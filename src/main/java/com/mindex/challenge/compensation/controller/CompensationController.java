package com.mindex.challenge.compensation.controller;

import com.mindex.challenge.compensation.data.Compensation;
import com.mindex.challenge.compensation.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    @PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);

        return compensationService.create(compensation);
    }

    @GetMapping("/compensation/{id}")
    public List<Compensation> read(@PathVariable String id) {
        LOG.debug("Received compensation read request for id [{}]", id);

        return compensationService.read(id);
    }

    @GetMapping("/compensation")
    public List<Compensation> readAll() {
        LOG.debug("Received read all compensation request");

        return compensationService.readAll();
    }
}