package com.hospitalapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class AdmissionState extends HospitalEntity {

    private static final long serialVersionUID = 1L;

    private Instant exitingDate;

    @NotNull
    private Instant enteringDate;

    @NotNull
    private Boolean discharge;

    @NotNull
    private String cause;

    @NotNull
    private String reason;

    public Boolean getDischarge() {
        return this.discharge;
    }

    public void setDischarge(Boolean discharge) {
        this.discharge = discharge;
    }

    public Instant getEnteringDate() {
        return this.enteringDate;
    }

    public void setEnteringDate(Instant enteringDate){
        this.enteringDate = enteringDate;
    }

    public void setExitingDate(Instant exitingDate) {
        this.exitingDate = exitingDate;
    }

    public Instant getExitingDate(){
        return this.exitingDate;
    }

    public String getReason(){
        return this.reason;
    }
    public void setReason(String reason){
        this.reason = reason;
    }
    public String getCause(){
        return this.cause;
    }
    public void setCause(String cause){
        this.cause = cause;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "patientId")
    private Patient patient;

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient){
        this.patient = patient;
    }
}
