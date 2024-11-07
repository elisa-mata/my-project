import { Component, Input } from '@angular/core';
import { ModalController, AlertController } from '@ionic/angular';
import { Department } from '../models/department.model';

@Component({
  selector: 'app-add-department-modal',
  templateUrl: './add-department-modal.component.html',
  styleUrls: ['./add-department-modal.component.scss'],
})
export class AddDepartmentModalComponent {
  @Input() department: Department = { name: '', code: '' };

  constructor(
    private modalController: ModalController,
    private alertController: AlertController // Import AlertController
  ) {}

  dismiss() {
    this.modalController.dismiss();
  }

  async confirm() {
    if (!this.department.name || !this.department.code) {
      const alert = await this.alertController.create({
        header: 'Error',
        message: 'All fields must be filled out.',
        buttons: ['OK']
      });

      await alert.present();
    } else {
      this.modalController.dismiss(this.department);
    }
  }
}
