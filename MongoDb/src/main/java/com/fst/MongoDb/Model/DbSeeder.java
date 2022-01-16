package com.fst.MongoDb.Model;

import com.fst.MongoDb.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DbSeeder implements CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository ;

    @Override
    public void run(String...strings) throws Exception {

        Employee employee1 = new Employee(
                    "Taylor Swift" ,
                    "taylor@gmail.com",
                    "Java" ,
                    "97586213" ,
                    "https://bootdey.com/img/Content/avatar/avatar3.png"

            ) ;
        Employee employee2 = new Employee(
                "Ella Marija" ,
                "ella@gmail.com",
                "JavaScript" ,
                "22598632" ,
                "https://bootdey.com/img/Content/avatar/avatar3.png"

        ) ;


            // drop all employees
            this.employeeRepository.deleteAll();

            // add new employees to the database
            List<Employee> employees = Arrays.asList(employee1, employee2);
            this.employeeRepository.insert(employees);
    };

    }
