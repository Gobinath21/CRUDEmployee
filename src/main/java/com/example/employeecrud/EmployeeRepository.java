package com.example.employeecrud;

import com.example.employeecrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    String FIND_BY_ROLE_QUERY = "SELECT * FROM employee WHERE role = :role";
    String UPDATE_EMPLOYEE_QUERY = "UPDATE employee SET name = :name, role = :role WHERE id = :id";
    @Query(value = FIND_BY_ROLE_QUERY, nativeQuery = true)
    List<Employee> findByRole(@Param("role") String role);


    @Modifying
    @Query(value = UPDATE_EMPLOYEE_QUERY, nativeQuery = true)
    void updateEmployee(@Param("id") Integer id, @Param("name") String name, @Param("role") String role);
}


