package com.fst.MongoDb.repo;

import com.fst.MongoDb.Model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EmployeeRepository extends MongoRepository <Employee, String > {
    Employee findEmployeeByEmail(String email);
}
