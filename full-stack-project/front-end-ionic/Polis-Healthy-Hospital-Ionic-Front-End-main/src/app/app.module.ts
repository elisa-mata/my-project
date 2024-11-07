import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouteReuseStrategy} from '@angular/router';
import {IonicModule, IonicRouteStrategy} from '@ionic/angular';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from "@angular/common/http";
import {CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import { HomeComponent } from './home/home.component';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {ManageDepartmentsComponent} from './manage-departments/manage-departments.component';
import {ManagePatientsComponent} from './manage-patients/manage-patients.component';
import {AddPatientModalComponent} from './add-patient-modal/add-patient-modal.component';

import {DepartmentService} from './services/department.service';
import {AddDepartmentModalComponent} from './add-department-modal/add-department-modal.component';  // Import the modal component
import {ManageAdmissionsModalComponent} from './manage-admissions-modal/manage-admissions-modal.component';
import {ManageClinicalDataComponent} from './manage-clinical-data/manage-clinical-data.component';
import {AdmissionStateService} from "./services/admission-state.services";
import {PatientService} from "./services/patient.services";
import {ClinicalDataService} from "./services/clinical-data.service";

@NgModule({
  declarations: [AppComponent, HomeComponent, ManageDepartmentsComponent, AddDepartmentModalComponent, ManagePatientsComponent, AddPatientModalComponent, ManageAdmissionsModalComponent, ManageClinicalDataComponent, 
  ],
  imports: [BrowserModule, IonicModule.forRoot(), AppRoutingModule, FormsModule,
    HttpClientModule],
  providers: [{
    provide: RouteReuseStrategy,
    useClass: IonicRouteStrategy
  }, DepartmentService, PatientService, AdmissionStateService, ClinicalDataService],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppModule {
}
