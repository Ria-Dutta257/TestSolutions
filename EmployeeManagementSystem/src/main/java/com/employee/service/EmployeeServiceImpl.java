package com.employee.service;

import com.employee.model.Employee;
import com.employee.model.Address;

public class EmployeeServiceImpl implements EmployeeService {

    private Employee employee;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void addEmployee() {
        System.out.println("Employee Added Successfully");
    }

    @Override
    public void updateEmployee() {
        System.out.println("Employee Updated");
    }

    @Override
    public void deleteEmployee() {
        System.out.println("Employee Deleted");
    }

    @Override
    public void getEmployee() {

        Address addr = employee.getAddress();

        System.out.println("Employee ID: " + employee.getId());
        System.out.println("Name: " + employee.getName());
        System.out.println("Email: " + employee.getEmail());
        System.out.println("Age: " + employee.getAge());
        System.out.println("Salary: " + employee.getSalary());

        System.out.println("City: " + addr.getCity());
        System.out.println("State: " + addr.getState());
        System.out.println("Country: " + addr.getCountry());
        System.out.println("Pincode: " + addr.getPincode());
    }
}