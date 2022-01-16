import { Component, OnInit } from '@angular/core';
import { Employee } from './employee';
import { EmployeeService } from './employee.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import {document} from "ngx-bootstrap/utils";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  // @ts-ignore
  public employees: Employee[];
  // @ts-ignore
  public editEmployee: Employee;
  // @ts-ignore
  public deleteEmployee: Employee;
  public title: string = "employee app";

  constructor(private employeeService: EmployeeService) {
  }

  ngOnInit() {
    this.getEmployees();
  }

  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe(
      (response: Employee[]) => {
        this.employees = response;
        console.log(this.employees);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }



  public onAddEmployee(addForm: NgForm) : void {
    document.getElementById('add-employee-form').click();
    this.employeeService.addEmployee(addForm.value).subscribe(
      (response:Employee []) => {
        console.log(response);
        this.getEmployees()
      } ,
      (error: HttpErrorResponse) => {
      alert(error.message);
  }
    ) ;

  }

  public onUpdateEmployee(employee: Employee) : void {
    this.employeeService.updateEmployee(employee).subscribe(
      (response:Employee []) => {
        console.log(response);
        this.getEmployees()
      } ,
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    ) ;

  }

  public onDeleteEmployee(employeeId: number): void {
    this.employeeService.deleteEmployee(employeeId).subscribe(
      (response: void) => {
        console.log(response);
        this.getEmployees();
      },
    );

  }

  public searchEmployees(key: string): void {
    console.log(key);
    const results: Employee[] = [];
    for (const employee of this.employees) {
      if (employee.name.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || employee.email.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || employee.phone.toLowerCase().indexOf(key.toLowerCase()) !== -1
        || employee.jobTitle.toLowerCase().indexOf(key.toLowerCase()) !== -1) {
        results.push(employee);
      }
    }
    this.employees = results;
    if (results.length === 0 || !key) {
      this.getEmployees();
    }
  }



  public onOpenModal (employee : any, mode: string) : void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button' ; //bc the default type is submit
    button.style.display = 'none';
    button.setAttribute('data-toggle' , 'modal') ;
    if (mode === 'add') {
      button.setAttribute('data-target' , '#modal-add-employee') ;

    }
    if (mode === 'edit') {
      this.editEmployee = employee;
      button.setAttribute('data-target' , '#modal-edit-employee') ;
    }
    if (mode === 'delete') {
      this.deleteEmployee = employee;
      button.setAttribute('data-target' , '#modal-delete-employee') ;
    }
    // @ts-ignore
    container.appendChild(button);
    button.click();
  }



}
