package com.hospitalapp.service;

import java.util.List;
import java.util.Optional;

import com.hospitalapp.model.Department;
import com.hospitalapp.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The DepartmentServiceBean encapsulates all business behaviors for operations on the Department entity model and some
 * related entities such as Department.
 *
 * @author Donaldo
 */
@Service
public class DepartmentServiceBean implements DepartmentService {

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceBean.class);

    /**
     * The Spring Data repository for Department entities.
     */
    @Autowired
    private transient DepartmentRepository departmentRepository;

    @Override
    public Optional<Department> getById(Long id) {
        logger.info("> getById");

        final Optional<Department> Department = departmentRepository.findById(id);

        logger.info("< getById");

        return Department;
    }

    @Override
    public List<Department> findAll(String search) {
        logger.info("> findAll");

        if (search == null || search.isEmpty()) {
            return departmentRepository.findAll();
        }

        return departmentRepository.findByNameContaining(search);
    }

    @Transactional
    @Override
    public Department upsert(final Department Department) {
        logger.info("> create");

        // Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
        if (Department.getId() != null) {
            final Department DepartmentToUpdate = departmentRepository.findById(Department.getId()).get();
            DepartmentToUpdate.setCode(Department.getCode());
            DepartmentToUpdate.setName(Department.getName());

            final Department updatedDepartment = departmentRepository.save(DepartmentToUpdate);

            logger.info("< update {}", Department.getId());
            return updatedDepartment;
        }

        final Department savedDepartment = departmentRepository.save(Department);

        logger.info("< create");

        return savedDepartment;
    }

    @Transactional
    @Override
    public void delete(final Long id) {
        logger.info("> delete {}", id);

        departmentRepository.deleteById(id);

        logger.info("< delete {}", id);
    }
}
