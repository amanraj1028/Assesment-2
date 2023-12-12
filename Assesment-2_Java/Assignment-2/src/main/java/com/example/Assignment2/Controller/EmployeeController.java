package com.example.Assignment2.Controller;

import com.example.Assignment2.Model.Employee;
import com.example.Assignment2.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService)
    {
        this.employeeService=employeeService;
    }

    // Endpoint to add an employee using HTTP POST method

    @PostMapping("add-employee")
    public String saveEmployee(@RequestBody Employee employee)
    {
        return employeeService.addEmployee(employee);
    }

    // Endpoint to retrieve all employees using HTTP GET method

    @GetMapping("get-employee")
    public String getEmployee()
    {
        return employeeService.getAllEmployees();
    }

    // Endpoint to update an employee using HTTP PUT method

    @PutMapping("/update/{id}")
    public String updateEmployee(@RequestBody Employee employee, @PathVariable String id) {return employeeService.updateEmployee(id,employee);}

    // Endpoint to delete an employee by ID using HTTP GET method

    @GetMapping("/del/{id}")
    public String  removeEmployeeById(@PathVariable String id) {
        return employeeService.removeEmpById(id);
    }

    // Endpoint to remove all employees using HTTP GET method

    @GetMapping("/delAll")
    public void removeAllEmployees() {
        employeeService.removeAllEmployees();
    }

}