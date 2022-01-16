package com.fst.MongoDb.service;

import com.fst.MongoDb.Model.Employee;
import com.fst.MongoDb.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService  {

    private final EmployeeRepository employeeRepository;

    public boolean deleteEmployee(String id) {
        boolean exists = this.employeeRepository.existsById(id) ;
        if (!exists) {
            return false ;
        }
        employeeRepository.deleteById(id);
        return true ;

    }

    public Employee addEmployee (Employee employee) {
        return employeeRepository.insert(employee) ;
    }

}
