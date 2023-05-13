package com.mindex.challenge.reportingStructure.service.impl;

import com.mindex.challenge.employee.data.Employee;
import com.mindex.challenge.employee.service.EmployeeService;
import com.mindex.challenge.reportingStructure.data.ReportingStructure;
import com.mindex.challenge.reportingStructure.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Calculate reporting structure for id [{}]", id);

        //if employee is not in the DB throw a 404
        Employee employee = employeeService.read(id);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid employeeId: " + id);
        }

        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);

        Set<String> allReports = new HashSet<>();
        findReports(employee, allReports);

        //the number of reports is the final size of the set
        reportingStructure.setNumberOfReports(allReports.size());

        return reportingStructure;
    }

    /*
    Recursively parse through the employee's direct reports, then look up their direct reports.
    Add each report in the tree to a set to keep track of the number of reports and make
    sure we don't double count employees who appear in the tree of reports twice.
     */
    private void findReports(Employee employee, Set<String> allReports) {
        if (employee.getDirectReports() != null) {
            for (Employee report: employee.getDirectReports()) {
                if (!allReports.contains(report.getEmployeeId())) { //if they are already in the Set we don't need to look at them again
                    allReports.add(report.getEmployeeId());
                    findReports(employeeService.read(report.getEmployeeId()), allReports);
                }
            }
        }
    }
}
