import { Component, OnInit } from '@angular/core';
import { ModalController, AlertController } from '@ionic/angular';
import { DepartmentService } from '../services/department.service';
import { AddDepartmentModalComponent } from '../add-department-modal/add-department-modal.component';
import { Department } from '../models/department.model';

@Component({
  selector: 'app-manage-departments',
  templateUrl: './manage-departments.component.html',
  styleUrls: ['./manage-departments.component.scss'],
})
export class ManageDepartmentsComponent implements OnInit {
  departments: Department[] = [];
  filteredDepartments: Department[] = [];
  searchTerm: string = '';

  constructor(
    private departmentService: DepartmentService,
    private modalController: ModalController,
    private alertController: AlertController
  ) {}

  ngOnInit() {
    this.departmentService.getDepartments().subscribe(departments => {
      this.departments = departments;
      this.filteredDepartments = this.departments;
    });
  }

  filterDepartments(event: any) {
    const searchTerm = event.target.value.toLowerCase();
    this.filteredDepartments = this.departments.filter(department =>
      department.name.toLowerCase().includes(searchTerm)
    );
  }

  async createDepartment() {
    const modal = await this.modalController.create({
      component: AddDepartmentModalComponent
    });

    modal.onDidDismiss().then((result) => {
      if (result.data) {
        this.departmentService.upsertDepartment(result.data).subscribe(response => {
          this.departments.push(response);
          this.filteredDepartments = [...this.departments];
        }, error => {
          console.error('Error adding patient', error);
        });
      }
    });

    return await modal.present();
  }

  async editDepartment(department: Department) {
    const modal = await this.modalController.create({
      component: AddDepartmentModalComponent,
      componentProps: { department: { ...department } }
    });

    modal.onDidDismiss().then((result) => {
      if (result.data) {
        this.departmentService.upsertDepartment(result.data).subscribe(response => {
          const index = this.departments.findIndex(dep => dep.id === department.id);
          if (index > -1) {
            this.departments[index] = response;
            this.filteredDepartments = [...this.departments];
          }
            }, error => {
            console.error('Error adding patient', error);
        });
      }
    });

    return await modal.present();
  }

  async confirmDelete(departmentId: number) {
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
            this.deleteDepartment(departmentId);
          }
        }
      ]
    });

    await alert.present();
  }

  deleteDepartment(departmentId: number) {
    this.departmentService.deleteDepartment(departmentId).subscribe(response => {
      this.filteredDepartments = this.filteredDepartments.filter(department => department.id !== departmentId);
      this.departments = this.departments.filter(department => department.id !== departmentId);
      console.log('Delete Department button clicked for department ID:', departmentId);
    }, error => {
      console.error('Error adding patient', error);
    });
  }
}
