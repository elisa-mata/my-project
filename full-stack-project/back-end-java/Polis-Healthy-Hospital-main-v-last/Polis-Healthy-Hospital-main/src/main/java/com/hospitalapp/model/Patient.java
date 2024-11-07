package com.hospitalapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class Patient extends HospitalEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private Instant birthDate;

    public Patient() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Instant birthDate) {
        this.birthDate = birthDate;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "departmentId")
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(final Department department) {
        this.department = department;
    }
}
