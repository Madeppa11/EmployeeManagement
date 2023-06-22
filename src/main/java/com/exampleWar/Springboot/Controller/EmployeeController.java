package com.exampleWar.Springboot.Controller;

import com.exampleWar.Springboot.Service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exampleWar.Springboot.Service.EmployeeService;
import com.exampleWar.Springboot.Service.impl.EmployeeServiceImpl;
import com.exampleWar.Springboot.model.Employee;
import com.exampleWar.Springboot.Service.*;
/*its is a conviniant annotation that combines @Controller and @ResponceBody, 
 * which elimenate the need to  annota every request handling method of controller class with responcseBody annotation
*/
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	/*
	 * @RequestBody annotation is allows us to requestBody and automatically convert
	 * into java Object build create methode for rest api
	 */

	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}

	/*
	 * get all employee list
	 */
	@GetMapping
	public List<Employee> getEmployee() {

		return employeeService.getEmployees();
	}

	/*
	 * fetch employee based on id url is====>http://localhost:8081/api/employees/
	 */
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeebyId(@PathVariable("id") long id) {
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(id), HttpStatus.OK);
	}

	/*
	 * update an employee element from repository //url is
	 * =====>>>>http://localhost:8081/api/employees/upd/4
	 */
	@PutMapping("/upd/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		Employee updatedEmployee = employeeService.updateEmployee(employee, id);
		return ResponseEntity.ok(updatedEmployee);
	}

	/*
	 * delet an employee element from repository url is
	 * =====>http://localhost:8081/api/employees/api/employees/2
	 */
	@DeleteMapping("/api/employees/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
		employeeService.deletEmployee(id);
		return new ResponseEntity<String>("Deleted Successfully....", HttpStatus.OK);
		// Delete logic
	}
}
