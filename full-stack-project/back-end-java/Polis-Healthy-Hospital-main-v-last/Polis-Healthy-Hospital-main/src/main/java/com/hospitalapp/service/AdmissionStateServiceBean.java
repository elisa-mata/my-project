package com.hospitalapp.service;

import java.util.List;
import java.util.Optional;

import com.hospitalapp.model.AdmissionState;
import com.hospitalapp.model.Patient;
import com.hospitalapp.repository.AdmissionStateRepository;
import com.hospitalapp.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdmissionStateServiceBean implements AdmissionStateService {

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(AdmissionStateServiceBean.class);

    /**
     * The Spring Data repository for AdmissionState entities.
     */
    @Autowired
    private transient AdmissionStateRepository admissionStateRepository;


    @Autowired
    private transient PatientRepository patientRepository;

    @Override
    public Optional<AdmissionState> getById(Long id) {
        logger.info("> getById");

        final Optional<AdmissionState> AdmissionState = admissionStateRepository.findById(id);

        logger.info("< getById");

        return AdmissionState;
    }

    @Override
    public List<AdmissionState> findByPatientId(Long patientId) {
        logger.info("> findByPatientId");

        final List<AdmissionState> AdmissionStates = admissionStateRepository.findByPatientId(patientId);

        logger.info("< findByPatientId");

        return AdmissionStates;
    }

    @Transactional
    @Override
    public AdmissionState upsert(final AdmissionState admissionState) {
        logger.info("> create");

        if (admissionState.getPatient() != null) {
            Patient patient = patientRepository.findById(admissionState.getPatient().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
            admissionState.setPatient(patient);
        }

        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (admissionState.getId() != null) {
            final AdmissionState clinicalDataToUpdate = admissionStateRepository.findById(admissionState.getId()).get();
            clinicalDataToUpdate.setDischarge(admissionState.getDischarge());
            clinicalDataToUpdate.setExitingDate(admissionState.getExitingDate());
            clinicalDataToUpdate.setEnteringDate(admissionState.getEnteringDate());

            final AdmissionState updatedAdmissionState = admissionStateRepository.save(clinicalDataToUpdate);

            logger.info("< update {}", updatedAdmissionState.getId());
            return clinicalDataToUpdate;
        }

        final AdmissionState savedAdmissionState = admissionStateRepository.save(admissionState);

        logger.info("< create");

        return savedAdmissionState;
    }

    @Transactional
    @Override
    public void delete(final Long id) {
        logger.info("> delete {}", id);

        admissionStateRepository.deleteById(id);

        logger.info("< delete {}", id);
    }
}
