import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from "../../environments/environment";
import {AdmissionState} from "../models/admission-state.model";
import {ClinicalData} from "../models/clinical-data.model";

@Injectable({
  providedIn: 'root'
})
export class ClinicalDataService {
  private apiUrl = `${environment.apiUrl}/clinicalData`;

  constructor(private http: HttpClient) { }

  getClinicalData(id: number): Observable<ClinicalData> {
    return this.http.get<any>(`${this.apiUrl}/${id}`).pipe(
      map(response => new ClinicalData(response))
    );
  }

  upsertClinicalData(data: ClinicalData): Observable<ClinicalData> {
    return this.http.post<any>(this.apiUrl, data.toApi()).pipe(
      map(response => new ClinicalData(response))
    );
  }

  deleteClinicalData(id: number): Observable<void> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }
}
