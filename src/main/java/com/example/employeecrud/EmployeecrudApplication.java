package com.example.employeecrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EmployeecrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeecrudApplication.class, args);
		System.out.println("ZYSI TECH");
	}

}
