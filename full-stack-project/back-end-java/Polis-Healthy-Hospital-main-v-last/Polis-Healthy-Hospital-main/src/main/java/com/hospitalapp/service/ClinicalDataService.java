package com.hospitalapp.service;

import com.hospitalapp.model.ClinicalData;

import java.util.List;
import java.util.Optional;

public interface ClinicalDataService {
    Optional<ClinicalData> getById(Long id);

    List<ClinicalData> findAll();

    ClinicalData upsert(ClinicalData p);

    void delete(Long id);
}
