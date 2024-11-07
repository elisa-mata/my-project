import { Component } from '@angular/core';
@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {
  public appPages = [
    { title: 'Home', url: '/home', icon: 'home' },
    { title: 'Manage Departments', url: '/folder/departments', icon: 'business-outline' },
    { title: 'Manage Patients', url: '/folder/patients', icon: 'people-outline' },
  ];

  constructor() {}
}
