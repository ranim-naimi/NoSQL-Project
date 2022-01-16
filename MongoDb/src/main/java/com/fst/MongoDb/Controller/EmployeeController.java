package com.fst.MongoDb.Controller;

import com.fst.MongoDb.Model.Employee;
import com.fst.MongoDb.repo.EmployeeRepository;
import com.fst.MongoDb.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    @GetMapping("/all")
    public List<Employee> getAll () {
        List<Employee> employees = this.employeeRepository.findAll();
        return employees;
    }
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee  (@RequestBody Employee employee) {
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>( newEmployee , HttpStatus.CREATED);

        }


    @PutMapping("/update")
    // save method works in both cases where the document exist or not
    public void updateEmployee  (@RequestBody Employee employee) {
        this.employeeRepository.save(employee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee (@PathVariable ("id") String id) {
        if (this.employeeService.deleteEmployee(id)) {
            return new ResponseEntity<>("employee deleted", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("employee not found" , HttpStatus.NOT_FOUND);

        }
    }
    @GetMapping("/all/{email}")
    public Employee getEmployee (@PathVariable("email") String email) {
        Employee employee = this.employeeRepository.findEmployeeByEmail(email);
        return employee;

    }
}
