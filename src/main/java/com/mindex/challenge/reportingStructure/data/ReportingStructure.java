package com.mindex.challenge.reportingStructure.data;

import com.mindex.challenge.employee.data.Employee;

/* I might refactor this class and @Compensation to be immutable
   it forces you to use the parameterized constructor which results in cleaner code imo
 */
public class ReportingStructure {
    private Employee employee;

    private int numberOfReports;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}
