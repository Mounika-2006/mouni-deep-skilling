package com.cognizant.employeejpademo.repository;

import com.cognizant.employeejpademo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee,Integer> {

}