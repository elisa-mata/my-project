package com.hospitalapp.service;

import com.hospitalapp.model.AdmissionState;

import java.util.List;
import java.util.Optional;

public interface AdmissionStateService {
    Optional<AdmissionState> getById(Long id);

    List<AdmissionState> findByPatientId(Long patientId);

    AdmissionState upsert(AdmissionState p);

    void delete(Long id);
}
