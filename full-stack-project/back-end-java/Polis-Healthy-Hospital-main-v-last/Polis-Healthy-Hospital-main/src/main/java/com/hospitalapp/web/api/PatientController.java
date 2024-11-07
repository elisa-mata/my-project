package com.hospitalapp.web.api;

import java.util.List;
import java.util.Optional;

import com.hospitalapp.model.Patient;
import com.hospitalapp.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The PatientController class is a RESTful web service controller. The <code>@RestController</code> annotation informs
 * Spring that each <code>@RequestMapping</code> method returns a <code>@ResponseBody</code>.
 *
 * @author Matt Warman
 */
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    /**
     * The PatientService business service.
     */
    @Autowired
    private transient PatientService patientService;
    

    /**
     * Web service endpoint to fetch all Patient entities. The service returns the collection of Patient entities as
     * JSON.
     *
     * @return A List of Patient objects.
     */
    @GetMapping
    public List<Patient> getPatients(@RequestParam(required = false) final String search) {
        logger.info("> getPatients");

        final List<Patient> patients = patientService.findAll(search);

        logger.info("< getPatients");
        return patients;
    }

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable final Long id) {
        logger.info("> getPatient");

        final Optional<Patient> patientOptional = patientService.getById(id);

        logger.info("< getPatient");

        return patientOptional.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createPatient(@RequestBody final Patient patient) {
        logger.info("> createPatient");

        final Patient savedPatient = patientService.upsert(patient);

        logger.info("< createPatient");
        return savedPatient;
    }

    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable("id") final Long id, @RequestBody final Patient patient) {
        logger.info("> updatePatient");

        patient.setId(id);

        final Patient updatedPatient = patientService.upsert(patient);

        logger.info("< updatePatient");
        return updatedPatient;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable("id") final Long id) {
        logger.info("> deletePatient");

        patientService.delete(id);

        logger.info("< deletePatient");
    }
}
