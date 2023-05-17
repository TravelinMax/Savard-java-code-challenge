package com.mindex.challenge.employee.data;

import java.util.List;

public class Employee {
    final private String employeeId;
    final private String firstName;
    final private String lastName;
    final private String position;
    final private String department;
    final private List<Employee> directReports;

    public Employee(String employeeId, String firstName, String lastName, String position, String department, List<Employee> directReports) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.department = department;
        this.directReports = directReports;
    }

    /**
     * basically a copy constructor
     */
    public Employee(String employeeId, Employee employee) {
        this.employeeId = employeeId;
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.position = employee.getPosition();
        this.department = employee.getDepartment();
        this.directReports = employee.getDirectReports();
    }

    public String getEmployeeId() {
        return employeeId;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

}
