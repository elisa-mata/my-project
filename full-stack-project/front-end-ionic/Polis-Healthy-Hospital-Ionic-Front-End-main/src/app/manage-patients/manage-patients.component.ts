import { Component, OnInit } from '@angular/core';
import { ModalController, AlertController } from '@ionic/angular';
import { AddPatientModalComponent } from '../add-patient-modal/add-patient-modal.component';
import { Router } from '@angular/router';
import { PatientService } from '../services/patient.services';
import { Patient } from '../models/patient.model';
import { AdmissionStateService } from '../services/admission-state.services';
import { AdmissionState } from '../models/admission-state.model';

@Component({
  selector: 'app-manage-patients',
  templateUrl: './manage-patients.component.html',
  styleUrls: ['./manage-patients.component.scss'],
})
export class ManagePatientsComponent implements OnInit {
  patients: Patient[] = [];
  filteredPatients: Patient[] = [];
  searchTerm: string = '';

  constructor(
    private patientService: PatientService,
    private admissionStateService: AdmissionStateService,
    private modalController: ModalController,
    private alertController: AlertController,
    private router: Router
  ) {}

  ngOnInit() {
    this.patientService.getPatients().subscribe(patients => {
      this.patients = patients;
      this.filteredPatients = this.patients;
    });
  }

  filterPatients(event: any) {
    const searchTerm = event.target.value.toLowerCase();
    this.filteredPatients = this.patients.filter(patient =>
      patient.name.toLowerCase().includes(searchTerm)
    );
  }

  async createPatient() {
    const modal = await this.modalController.create({
      component: AddPatientModalComponent
    });

    modal.onDidDismiss().then((result) => {
      if (result.data) {
        this.patientService.upsertPatient(result.data).subscribe(response => {
          this.patients.push(response);
          this.filteredPatients = [...this.patients];
        }, error => {
          console.error('Error adding patient', error);
        });
      }
    });

    return await modal.present();
  }

  async editPatient(patient: Patient) {
    const modal = await this.modalController.create({
      component: AddPatientModalComponent,
      componentProps: { patient: { ...patient }, isEditMode: true }
    });

    modal.onDidDismiss().then((result) => {
      if (result.data) {
        const index = this.patients.findIndex(p => p.id === patient.id);
        if (index > -1) {
          this.patients[index] = result.data;
          this.filteredPatients = [...this.patients];
        }
      }
    });

    return await modal.present();
  }

  async confirmDelete(patientId: number) {
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
            this.deletePatient(patientId);
          }
        }
      ]
    });

    await alert.present();
  }

  deletePatient(patientId: number) {
    this.patientService.deletePatient(patientId).subscribe(response => {
      this.patients = this.filteredPatients.filter(patient => patient.id !== patientId);
      this.filteredPatients = this.patients;
      console.log('Delete Patient button clicked for Patient ID:', patientId);
    }, error => {
      console.error('Error adding patient', error);
    });
  }

  async dischargePatient(patientId: number) {
    const alert = await this.alertController.create({
      header: 'Discharge Patient',
      inputs: [
        {
          name: 'dischargeReason',
          type: 'radio',
          label: 'Healthy',
          value: 'healthy'
        },
        {
          name: 'dischargeReason',
          type: 'radio',
          label: 'Transfer',
          value: 'transfer'
        },
        {
          name: 'dischargeReason',
          type: 'radio',
          label: 'Death',
          value: 'death'
        }
      ],
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          handler: () => {
            console.log('Discharge cancelled');
          }
        },
        {
          text: 'Confirm',
          handler: async (data) => {
            if (!data) {
              const alert = await this.alertController.create({
                header: 'Error',
                message: 'Please choose a reason for discharge.',
                buttons: ['OK']
              });
              await alert.present();
              return;
            }

            console.log('Patient discharged with reason:', data);
            const admission = new AdmissionState({
              discharge: true,
              enteringDate: new Date(),
              exitingDate: new Date(),
              reason: data,
              cause: data,
              patient: this.patients.find(p => p.id === patientId)
            });

            this.admissionStateService.upsertAdmissionSate(admission).subscribe(async response => {
              // Implement logic to handle the discharge reason
              await this.showDischargeConfirmation();
              console.log('Added admission state', response);
            }, error => {
              console.error('Error adding admission state', error);
            });
          }
        }
      ]
    });

    await alert.present();
  }

  async showDischargeConfirmation() {
    const alert = await this.alertController.create({
      header: 'Discharged!',
      buttons: ['OK']
    });

    await alert.present();
  }

  async manageAdmissions(patientId: number) {
    this.router.navigate(['/folder/manage-admissions', patientId] );
  }

  manageClinicalData(patient: Patient) {
    this.router.navigate(['/folder/manage-clinical-data', patient.id]);
  }
}
