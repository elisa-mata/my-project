import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ManageDepartmentsComponent } from './manage-departments/manage-departments.component';
import { ManagePatientsComponent } from './manage-patients/manage-patients.component';
import {ManageAdmissionsModalComponent } from './manage-admissions-modal/manage-admissions-modal.component';
import { ManageClinicalDataComponent } from './manage-clinical-data/manage-clinical-data.component';


const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' }, // Set default route to home
  { path: 'home', component: HomeComponent },

  {
    path: '',
    redirectTo: 'folder/departments',
    pathMatch: 'full'
  },

  { path: 'folder/departments',
  component: ManageDepartmentsComponent,
  data: { title: 'Manage Departments' }
  },
  {
    path: 'folder/patients',
    component: ManagePatientsComponent,
    data: { title: 'Manage Patients' }
  },
  { path: 'folder/manage-admissions/:patientId',
    component: ManageAdmissionsModalComponent
  },

  { path: 'folder/manage-clinical-data/:patientId',
  component: ManageClinicalDataComponent
  },
  {
    path: 'folder/:id',
    loadChildren: () => import('./folder/folder.module').then( m => m.FolderPageModule)
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
