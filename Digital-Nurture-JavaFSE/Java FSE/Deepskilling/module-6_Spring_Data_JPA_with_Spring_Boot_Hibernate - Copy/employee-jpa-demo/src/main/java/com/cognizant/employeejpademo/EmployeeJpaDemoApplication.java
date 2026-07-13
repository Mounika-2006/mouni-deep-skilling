package com.cognizant.employeejpademo;

import com.cognizant.employeejpademo.model.Employee;
import com.cognizant.employeejpademo.service.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeJpaDemoApplication {

    private static EmployeeService employeeService;

    public static void main(String[] args) {

        System.out.println("1. Main Started");

        ApplicationContext context =
                SpringApplication.run(EmployeeJpaDemoApplication.class, args);

        System.out.println("2. Spring Started");

        employeeService = context.getBean(EmployeeService.class);

        System.out.println("3. Service Loaded");

        testAddEmployee();

        System.out.println("4. Main Finished");
    }

    private static void testAddEmployee() {

        System.out.println("Inside Test Method");

        Employee employee = new Employee();

        employee.setId(101);
        employee.setName("Mounika");
        employee.setSalary(60000);

        employeeService.addEmployee(employee);

        System.out.println("Employee Saved");
    }
}