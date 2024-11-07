package com.hospitalapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Department extends HospitalEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    @NotNull
    private String code;

    public Department() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }
}
