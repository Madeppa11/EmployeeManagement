package com.exampleWar.Springboot.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exampleWar.Springboot.Service.EmployeeService;
import com.exampleWar.Springboot.exception.ResourceNotFoundException;
import com.exampleWar.Springboot.model.Employee;
import com.exampleWar.Springboot.repositary.EmployeeRepository;

import jakarta.transaction.Transactional;

//@Transactional: if we didnot declare this annotation in this class then also it will take from spring data jpa(it internally contatins @Transacttional)
@Service
public class EmployeeServiceImpl implements EmployeeService {

	// in a spring bean class if it has only one construcotr then it will
	// automatically consider @Autowire ,its not required to mention again
	private EmployeeRepository employeeRepository;

	// constructor injection
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee=employeeRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}
//		else {
//			throw new ResourceNotFoundException("Employee","id",id);
//		}
		return employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee","id",id));
		}

//	@Override
//	public Employee updEmployee(Employee employee,long id) {
//		Employee employe=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "id", id));
//		
//		employe.setFirstName(employee.getFirstName());
//		employe.setFirstName(employee.getSecondName());
//		employe.setFirstName(employee.getEmail());
//		
//		employeeRepository.save(employe);
//		
//		return employe;
//	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
        Employee existingEmployee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "id", id));
       
        existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setSecondName(employee.getSecondName());
		existingEmployee.setEmail(employee.getEmail());
		employeeRepository.save(existingEmployee);
        return existingEmployee;
	}

	@Override
	public void deletEmployee(long id) {
		
		Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "id", id));
		employeeRepository.deleteById(id);
		
	}
	


}
