package com.hospitalapp.web.api;

import com.hospitalapp.model.ClinicalData;
import com.hospitalapp.service.ClinicalDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clinicalData")
public class ClinicalDataController {

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(ClinicalDataController.class);

    /**
     * The admissionStateService business service.
     */
    @Autowired
    private transient ClinicalDataService clinicalDataService;

    @GetMapping
    public List<ClinicalData> getClinicalData() {
        logger.info("> getClinicalData");

        final List<ClinicalData> clinicalData = clinicalDataService.findAll();

        logger.info("< getClinicalData");

        return clinicalData;
    }

    @GetMapping("/{id}")
    public ClinicalData getClinicalData(@PathVariable final Long id) {
        logger.info("> getClinicalData");

        final Optional<ClinicalData> ClinicalDataOptional = clinicalDataService.getById(id);

        logger.info("< getClinicalData");

        return ClinicalDataOptional.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClinicalData createClinicalData(@RequestBody final ClinicalData admissionState) {
        logger.info("> upsertClinicalData");

        final ClinicalData savedClinicalData = clinicalDataService.upsert(admissionState);

        logger.info("< upsertClinicalData");
        return savedClinicalData;
    }

    @PutMapping("/{id}")
    public ClinicalData updateClinicalData(@PathVariable("id") final Long id, @RequestBody final ClinicalData admissionState) {
        logger.info("> updateClinicalData");

        admissionState.setId(id);

        final ClinicalData updatedClinicalData = clinicalDataService.upsert(admissionState);

        logger.info("< updateClinicalData");
        return updatedClinicalData;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClinicalData(@PathVariable("id") final Long id) {
        logger.info("> deleteClinicalData");

        clinicalDataService.delete(id);

        logger.info("< deleteClinicalData");
    }
}
