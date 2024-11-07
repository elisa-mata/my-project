package com.hospitalapp.service;

import java.util.List;
import java.util.Optional;

import com.hospitalapp.model.Department;
import com.hospitalapp.model.Patient;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * The PatientService interface defines all public business behaviors for operations on the Patient entity model and
 * some related entities such as Role.
 * </p>
 * <p>
 * This interface should be injected into PatientService clients, not the implementation bean.
 * </p>
 *
 * @author Donaldo
 */
public interface PatientService {

    /**
     * Find a Patient by the id.
     *
     * @param id A String username to query the repository.
     * @return An Optional wrapped Patient.
     */
    Optional<Patient> getById(Long id);

    List<Patient> findAll(@Param("name") String name);

    Patient upsert(Patient p);

    void delete(Long id);
}
