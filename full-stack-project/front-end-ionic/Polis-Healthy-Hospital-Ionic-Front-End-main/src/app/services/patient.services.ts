import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from "../../environments/environment";
import { Patient } from "../models/patient.model";

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  private apiUrl = `${environment.apiUrl}/patient`;

  constructor(private http: HttpClient) { }

  getPatients(search?: string): Observable<Patient[]> {
    return this.http.get<any[]>(`${this.apiUrl}${search ? `?search=${search}` : ""}`).pipe(
      map(response => response.map(d => new Patient(d)))
    );;
  }

  getPatient(id: number): Observable<Patient> {
    return this.http.get<any>(`${this.apiUrl}/${id}`).pipe(
      map(response => new Patient(response))
    );
  }

  upsertPatient(patient: Patient): Observable<Patient> {
    return this.http.post<any>(this.apiUrl, patient.toApi()).pipe(
      map(response => new Patient(response))
    );
  }

  deletePatient(id: number): Observable<void> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }
}
