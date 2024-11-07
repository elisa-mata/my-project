import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from "../../environments/environment";
import { Department } from "../models/department.model";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  private apiUrl = `${environment.apiUrl}/department`;

  constructor(private http: HttpClient) { }

  getDepartments(search?: string): Observable<Department[]> {
    return this.http.get<any[]>(`${this.apiUrl}${search ? `?search=${search}` : ""}`).pipe(
      map(response => response.map(d => new Department(d)))
    );;
  }

  getDepartment(id: number): Observable<Department> {
    return this.http.get<any>(`${this.apiUrl}/${id}`).pipe(
      map(response => new Department(response))
    );
  }

  upsertDepartment(department: Department): Observable<Department> {
    return this.http.post<any>(this.apiUrl, department).pipe(
      map(response => new Department(response))
    );
  }

  deleteDepartment(id: number): Observable<void> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }
}
