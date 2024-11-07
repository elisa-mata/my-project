package com.hospitalapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ClinicalData extends HospitalEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String clinicalRecord;

    public String getClinicalRecord() {
        return this.clinicalRecord;
    }

    public void setClinicalRecord(String clinicalRecord) {
        this.clinicalRecord = clinicalRecord;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "admissionStateId")
    private AdmissionState admissionState;

    public ClinicalData() {
        super();
    }

    public AdmissionState getAdmissionState(){
        return this.admissionState;
    }

    public void setAdmissionState(AdmissionState admissionState){
        this.admissionState = admissionState;
    }
}
