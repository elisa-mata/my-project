package com.hospitalapp.web.api;

import java.util.List;
import java.util.Optional;

import com.hospitalapp.model.Department;
import com.hospitalapp.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * The DepartmentController class is a RESTful web service controller. The <code>@RestController</code> annotation informs
 * Spring that each <code>@RequestMapping</code> method returns a <code>@ResponseBody</code>.
 *
 * @author Matt Warman
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    /**
     * The Logger for this Class.
     */
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    /**
     * The departmentService business service.
     */
    @Autowired
    private transient DepartmentService departmentService;


    /**
     * Web service endpoint to fetch all Department entities. The service returns the collection of Department entities as
     * JSON.
     *
     * @return A List of Department objects.
     */
    @GetMapping
    public List<Department> getDepartments(@RequestParam(required = false) final String search) {
        logger.info("> getDepartments");

        final List<Department> Departments = departmentService.findAll(search);

        logger.info("< getDepartments");
        return Departments;
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable final Long id) {
        logger.info("> getDepartment");

        final Optional<Department> DepartmentOptional = departmentService.getById(id);

        logger.info("< getDepartment");

        return DepartmentOptional.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department createDepartment(@RequestBody final Department department) {
        logger.info("> createDepartment");

        final Department savedDepartment = departmentService.upsert(department);

        logger.info("< createDepartment");
        return savedDepartment;
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable("id") final Long id, @RequestBody final Department department) {
        logger.info("> updateDepartment");

        department.setId(id);

        final Department updatedDepartment = departmentService.upsert(department);

        logger.info("< updateDepartment");
        return updatedDepartment;
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable("id") final Long id) {
        logger.info("> deleteDepartment");

        departmentService.delete(id);

        logger.info("< deleteDepartment");
    }
}
