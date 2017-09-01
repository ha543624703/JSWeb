package com.ylgk.dao;

import com.ylgk.form.Employee;

import java.util.List;

public interface EmployeeDao
{
    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    Employee getEmployeeById(Integer employeeId);

    void removeJbgz(Integer id);

    List listEmployees();
}
