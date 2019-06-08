package com.collabera.rest.springbootcrud.resource;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collabera.rest.springbootcrud.dao.EmployeeDao;
import com.collabera.rest.springbootcrud.model.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {

	@Autowired
	private EmployeeDao employeeDao;
	
	@PostMapping("/create")
	public Long createEmployee(@RequestBody Employee employee) {
		Employee savedEmployee = employeeDao.save(employee);
		return savedEmployee.getId();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable(value="id") Long id) {
		Optional<Employee> employee = employeeDao.findById(id);
		return employee.get();
	}
	
	@PutMapping("/update")
	public Long getEmployee(@RequestBody Employee employee) {
		Optional<Employee> theEmployee = employeeDao.findById(employee.getId());
		if(!theEmployee.isPresent())
			return null;
		theEmployee.get().setFullName(theEmployee.get().getFullName() + "_UPDATED");
		employeeDao.save(theEmployee.get());
		return employee.getId();
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteEmployee(@PathVariable(value="id") Long id) {
		Optional<Employee> theEmployee = employeeDao.findById(id);
		employeeDao.delete(theEmployee.get());
	}
}
