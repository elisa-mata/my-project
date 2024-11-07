export class Department {
  id?: number;
  name: string;
  code: string;

  constructor(data: any) {
    this.id = data.id;
    this.name = data.name;
    this.code = data.code;
  }
}
