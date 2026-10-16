package com.kloudspot.employeemanagement.repository;

import com.kloudspot.employeemanagement.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}