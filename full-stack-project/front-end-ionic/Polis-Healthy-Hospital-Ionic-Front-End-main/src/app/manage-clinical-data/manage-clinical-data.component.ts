import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertController } from '@ionic/angular';

interface ClinicalData {
  id: number;
  data: string;
}

@Component({
  selector: 'app-manage-clinical-data',
  templateUrl: './manage-clinical-data.component.html',
  styleUrls: ['./manage-clinical-data.component.scss'],
})
export class ManageClinicalDataComponent implements OnInit {
  clinicalData: ClinicalData[] = [];
  filteredClinicalData: ClinicalData[] = [];
  searchTerm: string = '';
  patientName: string = '';

  constructor(private alertController: AlertController, private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.patientName = params['name'] || '';
    });

    this.clinicalData = [
      { id: 1, data: 'Blood Test Result' },
      { id: 2, data: 'MRI Scan Result' },
      { id: 3, data: 'X-Ray Result' },
      // Add more clinical data as needed
    ];
    this.filteredClinicalData = this.clinicalData;
  }

  filterClinicalData(event: any) {
    const searchTerm = event.target.value.toLowerCase();
    this.filteredClinicalData = this.clinicalData.filter(cd =>
      cd.data.toLowerCase().includes(searchTerm)
    );
  }

  async addNewRecord() {
    const alert = await this.alertController.create({
      header: 'Add New Record',
      inputs: [
        {
          name: 'newClinicalData',
          type: 'text',
          placeholder: 'Enter new clinical data'
        }
      ],
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          handler: () => {
            console.log('Add new record cancelled');
          }
        },
        {
          text: 'Confirm',
          handler: async (data) => {
            if (!data.newClinicalData) {
              const alert = await this.alertController.create({
                header: 'Error',
                message: 'The clinical data should not be blank',
                buttons: ['OK']
              });

              await alert.present();
              return false;
            } else {
              const newRecord = {
                id: this.clinicalData.length + 1,
                data: data.newClinicalData
              };
              this.clinicalData.push(newRecord);
              this.filteredClinicalData = [...this.clinicalData];
              console.log('New clinical data added:', data.newClinicalData);
              return true;
            }
          }
        }
      ]
    });

    await alert.present();
  }

  async editClinicalData(clinicalData: ClinicalData) {
    const alert = await this.alertController.create({
      header: 'Edit Clinical Data',
      inputs: [
        {
          name: 'editedClinicalData',
          type: 'text',
          value: clinicalData.data,
          placeholder: 'Enter clinical data'
        }
      ],
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          handler: () => {
            console.log('Edit clinical data cancelled');
          }
        },
        {
          text: 'Confirm',
          handler: async (data) => {
            if (!data.editedClinicalData) {
              const alert = await this.alertController.create({
                header: 'Error',
                message: 'The clinical data should not be blank',
                buttons: ['OK']
              });

              await alert.present();
              return false;
            } else {
              const index = this.clinicalData.findIndex(cd => cd.id === clinicalData.id);
              if (index > -1) {
                this.clinicalData[index].data = data.editedClinicalData;
                this.filteredClinicalData = [...this.clinicalData];
                console.log('Clinical data edited:', data.editedClinicalData);
              }
              return true;
            }
          }
        }
      ]
    });

    await alert.present();
  }

  async confirmDeleteClinicalData(data: ClinicalData) {
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
            this.deleteClinicalData(data);
          }
        }
      ]
    });

    await alert.present();
  }

  deleteClinicalData(data: ClinicalData) {
    console.log('Delete button clicked for clinical data:', data);
    this.clinicalData = this.clinicalData.filter(cd => cd.id !== data.id);
    this.filteredClinicalData = this.filteredClinicalData.filter(cd => cd.id !== data.id);
  }

  goBack() {
    this.router.navigate(['/folder/patients']);
  }
}
