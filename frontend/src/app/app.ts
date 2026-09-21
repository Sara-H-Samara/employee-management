import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { EmployeeService, Employee } from './employee.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  employees: Employee[] = [];
  averageSalary = 0;

  newType = 'Developer';
  newName = '';
  newBaseSalary: number | null = null;
  newExtra: number | null = null;   // bonus لو Developer، teamBonus لو Manager

  constructor(private employeeService: EmployeeService) {}

  ngOnInit() {
    this.loadEmployees();
    this.loadAverage();
  }

  loadEmployees() {
    this.employeeService.getEmployees().subscribe(data => this.employees = data);
  }

  loadAverage() {
    this.employeeService.getAverageSalary().subscribe(avg => this.averageSalary = avg);
  }

  addEmployee() {
    if (!this.newName.trim() || !this.newBaseSalary || !this.newExtra) return;

    this.employeeService.addEmployee({
      type: this.newType,
      name: this.newName,
      baseSalary: this.newBaseSalary,
      extra: this.newExtra
    }).subscribe(() => {
      this.newName = '';
      this.newBaseSalary = null;
      this.newExtra = null;
      this.loadEmployees();
      this.loadAverage();
    });
  }

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id).subscribe(() => {
      this.loadEmployees();
      this.loadAverage();
    });
  }
}