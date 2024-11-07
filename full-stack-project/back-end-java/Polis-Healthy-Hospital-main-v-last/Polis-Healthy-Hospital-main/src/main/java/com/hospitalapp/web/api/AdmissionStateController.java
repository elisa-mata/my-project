package com.hospitalapp.web.api;

import java.util.List;
import java.util.Optional;

import com.hospitalapp.model.AdmissionState;
import com.hospitalapp.service.AdmissionStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admissionState")
public class AdmissionStateController {

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(AdmissionStateController.class);

    /**
     * The admissionStateService business service.
     */
    @Autowired
    private transient AdmissionStateService admissionStateService;

    @GetMapping("/{patientId}")
    public List<AdmissionState> getAdmissionState(@PathVariable final Long patientId) {
        logger.info("> getAdmissionState");

        final List<AdmissionState> admissionStates = admissionStateService.findByPatientId(patientId);

        logger.info("< getAdmissionState");

        return admissionStates;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdmissionState createAdmissionState(@RequestBody final AdmissionState admissionState) {
        logger.info("> upsertAdmissionState");

        final AdmissionState savedAdmissionState = admissionStateService.upsert(admissionState);

        logger.info("< upsertAdmissionState");
        return savedAdmissionState;
    }

    @PutMapping("/{id}")
    public AdmissionState updateAdmissionState(@PathVariable("id") final Long id, @RequestBody final AdmissionState admissionState) {
        logger.info("> updateAdmissionState");

        admissionState.setId(id);

        final AdmissionState updatedAdmissionState = admissionStateService.upsert(admissionState);

        logger.info("< updateAdmissionState");
        return updatedAdmissionState;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmissionState(@PathVariable("id") final Long id) {
        logger.info("> deleteAdmissionState");

        admissionStateService.delete(id);

        logger.info("< deleteAdmissionState");
    }
}
