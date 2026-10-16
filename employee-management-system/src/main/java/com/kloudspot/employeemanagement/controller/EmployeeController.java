package com.kloudspot.employeemanagement.controller;

import com.kloudspot.employeemanagement.Employee;
import com.kloudspot.employeemanagement.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee details) {

        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(details.getName());
                    employee.setEmail(details.getEmail());
                    employee.setPhoneNumber(details.getPhoneNumber());
                    employee.setDepartment(details.getDepartment());
                    employee.setDesignation(details.getDesignation());
                    employee.setDateOfJoining(details.getDateOfJoining());
                    employee.setSalary(details.getSalary());
                    employee.setStatus(details.getStatus());

                    return ResponseEntity.ok(employeeRepository.save(employee));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        if (!employeeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        employeeRepository.deleteById(id);
        return ResponseEntity.ok("Employee deleted successfully!");
    }
}