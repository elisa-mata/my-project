package com.hospitalapp.repository;

import com.hospitalapp.model.AdmissionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionStateRepository extends JpaRepository<AdmissionState, Long> {
    List<AdmissionState> findByPatientId(Long patientId);
}
