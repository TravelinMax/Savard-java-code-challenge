package com.mindex.challenge.compensation.service.impl;

import com.mindex.challenge.compensation.dao.CompensationRepository;
import com.mindex.challenge.compensation.data.Compensation;
import com.mindex.challenge.compensation.service.CompensationService;
import com.mindex.challenge.employee.data.Employee;
import com.mindex.challenge.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);

        /* Check if employee exists in the system */
        Employee employee = employeeService.read(compensation.getEmployee().getEmployeeId());
        /* The employee service itself will throw a 404 but I don't like to assume */
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid employeeId: " + compensation.getEmployee().getEmployeeId());
        }
        return compensationRepository.insert(compensation);
    }

    /*
    returns a list because each employee can have multiple compensation (multiple dates)
     */
    @Override
    public List<Compensation> read(String id) {
        LOG.debug("Reading compensation with employee id [{}]", id);
        /* Check if employee exists in the system */
        Employee employee = employeeService.read(id);
        /* The employee service itself will throw a 404 but I don't like to assume */
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid employeeId: " + id);
        }
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
