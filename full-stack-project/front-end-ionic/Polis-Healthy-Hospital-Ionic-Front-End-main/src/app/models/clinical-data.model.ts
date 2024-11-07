import {AdmissionState} from "./admission-state.model";

export class ClinicalData {
  id?: number;
  clinicalRecord: string;
  admissionState: AdmissionState;

  constructor(data: any) {
    this.id = data.id;
    this.clinicalRecord = data.clinicalRecord;
    this.admissionState = new AdmissionState(data.admissionState);
  }

  public toApi = () => {
    return {
      id: this.id,
      clinicalRecord: this.clinicalRecord,
      admissionState: this.admissionState.toApi(),
    }
  }
}
