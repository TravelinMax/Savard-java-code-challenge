package com.mindex.challenge.employee.service;

import com.mindex.challenge.employee.data.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);

    /**
     * added just for fun
     */
    List<Employee> readAll();
    Employee update(Employee employee);
}
