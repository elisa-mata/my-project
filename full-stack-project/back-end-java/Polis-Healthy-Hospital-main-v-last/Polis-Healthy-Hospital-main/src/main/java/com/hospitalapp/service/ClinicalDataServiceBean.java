package com.hospitalapp.service;

import com.hospitalapp.model.AdmissionState;
import com.hospitalapp.model.ClinicalData;
import com.hospitalapp.model.Department;
import com.hospitalapp.repository.AdmissionStateRepository;
import com.hospitalapp.repository.ClinicalDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicalDataServiceBean implements ClinicalDataService {

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(ClinicalDataServiceBean.class);

    /**
     * The Spring Data repository for AdmissionState entities.
     */
    @Autowired
    private transient ClinicalDataRepository clinicalDataRepository;

    @Autowired
    private transient AdmissionStateRepository admissionStateRepository;

    @Override
    public Optional<ClinicalData> getById(Long id) {
        logger.info("> getById");

        final Optional<ClinicalData> ClinicalData = clinicalDataRepository.findById(id);

        logger.info("< getById");

        return ClinicalData;
    }

    @Override
    public List<ClinicalData> findAll() {
        logger.info("> findAll");

        final List<ClinicalData> ClinicalDatas = clinicalDataRepository.findAll();

        logger.info("< findAll");

        return ClinicalDatas;
    }

    @Transactional
    @Override
    public ClinicalData upsert(final ClinicalData clinicalData) {
        logger.info("> create");

        if (clinicalData.getAdmissionState() != null) {
            AdmissionState admissionState = admissionStateRepository.findById(clinicalData.getAdmissionState().getId())
                    .orElseThrow(() -> new IllegalArgumentException("AdmissionState not found"));
            clinicalData.setAdmissionState(admissionState);
        }

        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (clinicalData.getId() != null) {
            final ClinicalData clinicalDataToUpdate = clinicalDataRepository.findById(clinicalData.getId()).get();
            clinicalDataToUpdate.setClinicalRecord(clinicalData.getClinicalRecord());

            final ClinicalData updatedDepartment = clinicalDataRepository.save(clinicalDataToUpdate);

            logger.info("< update {}", updatedDepartment.getId());
            return clinicalDataToUpdate;
        }

        final ClinicalData savedClinicalData = clinicalDataRepository.save(clinicalData);

        logger.info("< create");

        return savedClinicalData;
    }

    @Transactional
    @Override
    public void delete(final Long id) {
        logger.info("> delete {}", id);

        clinicalDataRepository.deleteById(id);

        logger.info("< delete {}", id);
    }
}
