import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Employee {
  id: number;
  name: string;
  type: string;
  baseSalary: number;
  calculatedSalary: number;
}

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private baseUrl = 'http://localhost:8080/api/employees';

  constructor(private http: HttpClient) {}

  getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.baseUrl);
  }

  getAverageSalary(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/average-salary`);
  }

  addEmployee(data: { type: string; name: string; baseSalary: number; extra: number }): Observable<Employee> {
    return this.http.post<Employee>(this.baseUrl, data);
  }

  deleteEmployee(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}