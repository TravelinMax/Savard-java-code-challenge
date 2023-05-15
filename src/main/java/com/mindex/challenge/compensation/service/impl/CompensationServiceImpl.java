package com.mindex.challenge.compensation.service.impl;

import com.mindex.challenge.compensation.dao.CompensationRepository;
import com.mindex.challenge.compensation.data.Compensation;
import com.mindex.challenge.compensation.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);

        return compensationRepository.insert(compensation);
    }

    /*
    returns a list because each employee can have multiple compensation (multiple dates)
     */
    @Override
    public List<Compensation> read(String id) {
        LOG.debug("Reading compensation with employee id [{}]", id);

        return compensationRepository.findByEmployeeEmployeeId(id);
    }

    /**
     * added just for fun
     */
    @Override
    public List<Compensation> readAll() {
        LOG.debug("Reading all compensation");

        return compensationRepository.findAll();
    }
}
