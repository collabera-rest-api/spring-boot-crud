package com.collabera.rest.springbootcrud.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collabera.rest.springbootcrud.model.Employee;

@Transactional
public interface EmployeeDao extends JpaRepository<Employee, Long> {

}
