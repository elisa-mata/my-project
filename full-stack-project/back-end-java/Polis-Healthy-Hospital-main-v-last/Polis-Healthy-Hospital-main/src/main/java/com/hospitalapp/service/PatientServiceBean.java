package com.hospitalapp.service;

import java.util.List;
import java.util.Optional;

import com.hospitalapp.model.Department;
import com.hospitalapp.model.Patient;
import com.hospitalapp.repository.DepartmentRepository;
import com.hospitalapp.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The PatientServiceBean encapsulates all business behaviors for operations on the Patient entity model and some
 * related entities such as Department.
 *
 * @author Donaldo
 */
@Service
public class PatientServiceBean implements PatientService {

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(PatientServiceBean.class);

    /**
     * The Spring Data repository for Patient entities.
     */
    @Autowired
    private transient PatientRepository patientRepository;

    @Autowired
    private transient DepartmentRepository departmentRepository;

    @Override
    public Optional<Patient> getById(Long id) {
        logger.info("> getById");

        final Optional<Patient> patient = patientRepository.findById(id);

        logger.info("< getById");

        return patient;
    }

    @Override
    public List<Patient> findAll(String search) {
        logger.info("> findAll");

        if (search == null || search.isEmpty()) {
            return patientRepository.findAll();
        }

        return patientRepository.findByNameContaining(search);
    }

    @Transactional
    @Override
    public Patient upsert(final Patient patient) {
        logger.info("> create");

        if (patient.getDepartment() != null) {
            Department department = departmentRepository.findById(patient.getDepartment().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Department not found"));
            patient.setDepartment(department);
        }

        if (patient.getId() != null) {
            logger.info("> update {}", patient.getId());

            final Patient patientToUpdate = patientRepository.findById(patient.getId()).get();
            patientToUpdate.setBirthDate(patient.getBirthDate());
            patientToUpdate.setDepartment(patient.getDepartment());
            patientToUpdate.setLastName(patient.getLastName());
            patientToUpdate.setName(patient.getName());

            final Patient updatedPatient = patientRepository.save(patientToUpdate);

            logger.info("< update {}", patient.getId());
            return updatedPatient;
        }

        final Patient savedPatient = patientRepository.save(patient);

        logger.info("< create");

        return savedPatient;
    }

    @Transactional
    @Override
    public void delete(final Long id) {
        logger.info("> delete {}", id);

        patientRepository.deleteById(id);

        logger.info("< delete {}", id);
    }
}
