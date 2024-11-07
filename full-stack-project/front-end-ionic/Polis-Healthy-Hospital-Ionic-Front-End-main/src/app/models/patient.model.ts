import {Department} from "./department.model";

export class Patient {
  id?: number;
  name: string;
  lastName: string;
  birthDate: Date;
  department: Department

  constructor(data: any) {
    console.log('data', data)
    this.id = data.id;
    this.name = data.name;
    this.lastName = data.lastName;
    this.birthDate = new Date(data.birthDate);
    this.department = new Department(data.department);
  }

  toApi (): any {
    return {
      id: this.id,
      name: this.name,
      lastName: this.lastName,
      department: this.department,
      birthDate: new Date(this.birthDate.toString()).toISOString()
    }
  }
}
