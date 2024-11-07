package com.hospitalapp.service;

import com.hospitalapp.model.Department;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Optional<Department> getById(Long id);

    List<Department> findAll(@Param("name") String name);

    Department upsert(Department p);

    void delete(Long id);
}
