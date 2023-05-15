package com.mindex.challenge.reportingStructure.data;

import com.mindex.challenge.employee.data.Employee;

/* I might refactor this class and @Compensation to be immutable */
public class ReportingStructure {
    final private Employee employee;

    final private int numberOfReports;

    public ReportingStructure(Employee employee, int numberOfReports) {
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }
}
