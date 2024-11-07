import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from "../../environments/environment";
import {AdmissionState} from "../models/admission-state.model";

@Injectable({
  providedIn: 'root'
})
export class AdmissionStateService {
  private apiUrl = `${environment.apiUrl}/admissionState`;

  constructor(private http: HttpClient) { }

  getAdmissionStates(id: number): Observable<AdmissionState[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${id}`).pipe(
      map(response => response.map(r => new AdmissionState(r)))
    );
  }

  upsertAdmissionSate(admissionState: AdmissionState): Observable<AdmissionState> {
    return this.http.post<any>(this.apiUrl, admissionState.toApi()).pipe(
      map(response => new AdmissionState(response))
    );
  }

  deleteAdmissionSate(id: number): Observable<void> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }
}
