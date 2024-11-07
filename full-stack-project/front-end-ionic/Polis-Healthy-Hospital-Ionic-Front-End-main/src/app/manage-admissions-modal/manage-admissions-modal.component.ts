import { Component, OnInit } from '@angular/core';
import { AlertController } from '@ionic/angular';
import { ActivatedRoute } from "@angular/router";
import { AdmissionStateService } from "../services/admission-state.services";
import { AdmissionState } from "../models/admission-state.model";
import {Department} from "../models/department.model";
import {Patient} from "../models/patient.model";
import {PatientService} from "../services/patient.services";
import {DepartmentService} from "../services/department.service";

@Component({
  selector: 'app-manage-admissions-modal',
  templateUrl: './manage-admissions-modal.component.html',
  styleUrls: ['./manage-admissions-modal.component.scss'],
})
export class ManageAdmissionsModalComponent implements OnInit   {
  admissions: AdmissionState[] = [];
  departments: Department[] = [];
  filteredDepartments: Department[] = [];
  patient: Patient = new Patient({
    id: 0,
    department: {
      code: "",
      name: ""
    }
  });
  searchTerm: string = '';
  patientId: number;

  constructor(
    private route: ActivatedRoute,
    private admissionService: AdmissionStateService,
    private patientService: PatientService,
    private departmentService: DepartmentService,
              private alertController: AlertController) {}

  ngOnInit() {
    this.patientId = Number(this.route.snapshot.paramMap.get('patientId'));
    this.admissionService.getAdmissionStates(this.patientId).subscribe(response => {
      this.admissions = response;
    });
    this.departmentService.getDepartments().subscribe(response => {
      this.departments = response;
      this.filteredDepartments = this.departments;
    });
    this.patientService.getPatient(this.patientId).subscribe(response => {
      this.patient = response;
    })
  }

  filterDepartments(event: any) {
    const searchTerm = event.target.value.toLowerCase();
    this.filteredDepartments = this.departments.filter(admission =>
      admission.name.toLowerCase().includes(searchTerm)
    );
  }

  async transfer(department: Department) {
    const alert = await this.alertController.create({
      header: 'Transfer Confirm',
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          handler: () => {
            console.log('Transfer cancelled');
          }
        },
        {
          text: 'Confirm',
          handler: (data) => {
            this.patientService.upsertPatient( new Patient({
              ...this.patient.toApi(),
              department
            })).subscribe(async response => {
              this.patient = response;
              this.admissions = this.admissions.map(a => ({...a, patient: response }))
              await this.showTransferConfirmation();
            }, error => console.log('Error transferring patient', error))
          }
        }
      ]
    });

    await alert.present();
  }

  async showTransferConfirmation() {
    const alert = await this.alertController.create({
      header: 'Transferred!',
      buttons: ['OK']
    });

    await alert.present();
  }

  async confirmDelete(id: number) {
    const alert = await this.alertController.create({
      header: 'Confirm Deletion',
      message: 'Do you confirm deletion?',
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          handler: () => {
            console.log('Delete cancelled');
          }
        },
        {
          text: 'Confirm',
          handler: () => {
            this.deleteAdmission(id);
          }
        }
      ]
    });

    await alert.present();
  }

  deleteAdmission(id: number) {
    this.admissionService.deleteAdmissionSate(id).subscribe(response => {
      this.admissions = this.admissions.filter(a => a.id !== id);
      console.log('Delete admission button clicked for ID:', id);
    }, error => {
      console.error('Error deleting admission', error);
    });
  }
}
