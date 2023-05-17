package com.mindex.challenge.compensation.data;

import com.mindex.challenge.employee.data.Employee;

import java.time.LocalDate;

public class Compensation {
    final private Employee employee;

    final private int salary;

    final private LocalDate effectiveDate;

    public Compensation(Employee employee, int salary, LocalDate effectiveDate) {
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getSalary() {
        return salary;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

}