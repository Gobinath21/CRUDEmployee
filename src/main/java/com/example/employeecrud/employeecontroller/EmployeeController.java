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
    public Employee getEmployeeId(@PathVariable int id) {
        Employee employee = employeeRepository.findById(id).get();
        return employee;
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

   //CHANGE NAME AND ROLL  USING ID
   //localhost:9090/employee/update/"{id}"
    @PutMapping("/employee/update/{id}")
    @Transactional
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Integer id,
            @RequestBody Map<String, String> requestBody) {

        String newName = requestBody.get("name");
        String newRole = requestBody.get("role");

        if (newName == null && newRole == null) {
            return ResponseEntity.badRequest().build();
        }
        employeeRepository.updateEmployee(id, newName, newRole);
        return employeeRepository.findById(id)
                .map(employee -> ResponseEntity.ok(employee))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}



