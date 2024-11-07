package com.hospitalapp.service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.hospitalapp.AbstractTest;
import com.hospitalapp.BasicTransactionalTest;
import com.hospitalapp.model.Department;

/**
 * Unit test methods for the departmentService and departmentServiceBean.
 * 
 * @author Matt Warman
 */
@RunWith(SpringRunner.class)
@BasicTransactionalTest
public class DepartmentServiceTest extends AbstractTest {

    /**
     * Constant 'test'.
     */
    private static final String VALUE_TEXT = "test";

    /**
     * The departmentService business service.
     */
    @Autowired
    private transient DepartmentService departmentService;

    @Override
    public void doBeforeEachTest() {

    }

    @Override
    public void doAfterEachTest() {
        // perform test clean up
    }

    /**
     * Test fetch a collection of Departments.
     */
    @Test
    public void testGetDepartments() {

        final Collection<Department> Departments = departmentService.findAll(null);

        Assert.assertNotNull("failure - expected not null", Departments);
        Assert.assertEquals("failure - expected 0 Departments", 0, Departments.size());

    }

    /**
     * Test fetch a single Department with invalid identifier.
     */
    @Test
    public void testGetDepartmentNotFound() {

        final Long id = Long.MAX_VALUE;

        final Optional<Department> departmentOptional = departmentService.getById(id);

        Assert.assertTrue("failure - expected null", departmentOptional.isEmpty());
    }

    /**
     * Test create a Department.
     */
    @Test
    public void testCreateDepartment() {

        final Department department = new Department();
        department.setName(VALUE_TEXT);
        department.setCode(VALUE_TEXT);

        final Department createdDepartment = departmentService.upsert(department);

        Assert.assertNotNull("failure - expected department not null", createdDepartment);
        Assert.assertNotNull("failure - expected department.id not null", createdDepartment.getId());
        Assert.assertEquals("failure - expected department.text match", VALUE_TEXT, createdDepartment.getName());

        final List<Department> departments = departmentService.findAll(null);

        Assert.assertEquals("failure - expected 1 departments", 1, departments.size());

    }

    /**
     * Test update a Department which does not exist.
     */
    @Test
    public void testUpdateDepartmentNotFound() {

        final Department department = new Department();
        department.setName("test");
        department.setCode("test");

        try {
            departmentService.upsert(department);
            final List<Department> departments = departmentService.findAll(null);
            Assert.assertEquals("failure - expected 0 department", 1, departments.size());
        } catch (NoSuchElementException ex) {
            Assert.fail("failure - expected exception");
        }

    }

}
