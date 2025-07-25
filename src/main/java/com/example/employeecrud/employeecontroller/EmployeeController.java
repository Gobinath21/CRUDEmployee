package com.example.employeecrud.employeecontroller;

import com.example.employeecrud.EmployeeRepository;
import com.example.employeecrud.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    //SHOW ALL EMPLOYEES
    //localhost:9090/getallemployee
    @GetMapping("/employee/getallemployee")
    public List<Employee> showAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    //GET EMPLOYEE BY ID
    //localhost:9090/employee/{id}
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeId(@PathVariable int id) {
        return employeeRepository.findById(id)
                .map(employee -> ResponseEntity.ok(employee))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    //GET EMPLOYEE BY ROLE
    //localhost:9090/employee/{role}
    @GetMapping("/employee/role/{role}")
    public List<Employee> getEmployeesByRole(@PathVariable String role) {
        return employeeRepository.findByRole(role);
    }

    // ADDING A EMPLOYEE
    //localhost:9090/employee/addemployee}
    //USE REQUEST BODY
   @PostMapping("/employee/addemployee")
    public void addEmployye(@RequestBody Employee employee){
        employeeRepository.save(employee);
   }

   //CHANGE NAME, SALARY AND ROLE USING ID
   //localhost:9090/employee/update/"{id}"
    @PutMapping("/employee/update/{id}")
    @Transactional
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Integer id,
            @RequestBody Map<String, String> requestBody) {

        String newName = requestBody.get("name");
        String newSalary = requestBody.get("salary");
        String newRole = requestBody.get("role");

        if (newName == null && newSalary == null && newRole == null) {
            return ResponseEntity.badRequest().build();
        }
        employeeRepository.updateEmployee(id, newName, newSalary, newRole);
        return employeeRepository.findById(id)
                .map(employee -> ResponseEntity.ok(employee))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}



