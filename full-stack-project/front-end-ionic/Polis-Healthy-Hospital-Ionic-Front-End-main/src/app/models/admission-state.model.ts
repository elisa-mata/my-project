import {Patient} from "./patient.model";

export class AdmissionState {
  id?: number;
  discharge: boolean;
  reason: string;
  cause: string;
  enteringDate: Date;
  exitingDate?: Date;
  patient: Patient;

  constructor(data: any) {
    this.id = data.id;
    this.discharge = data.discharge;
    this.enteringDate = new Date(data.enteringDate);
    this.exitingDate = data.exitingDate ? new Date(data.exitingDate) : null;
    this.patient = new Patient(data.patient);
    this.reason = data.reason;
    this.cause = data.cause;
  }

  public toApi = () => {
    return {
      id: this.id,
      discharge: this.discharge,
      reason: this.reason,
      cause: this.cause,
      enteringDate: new Date (this.enteringDate.toString()).toISOString(),
      exitingDate: this.exitingDate ? new Date (this.exitingDate.toString()).toISOString(): null,
      patient: this.patient.toApi(),
    }
  }
}
