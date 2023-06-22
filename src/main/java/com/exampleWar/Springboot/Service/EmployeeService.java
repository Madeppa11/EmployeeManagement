package com.exampleWar.Springboot.Service;

import java.util.List;

import com.exampleWar.Springboot.model.Employee;

public interface EmployeeService {
   public Employee saveEmployee(Employee employee);
   
   public List<Employee> getEmployees();
   
   public Employee getEmployeeById(long id);
   
 //  public Employee updateEmployee(Employee employee,long id);

public Employee updateEmployee(Employee employee, long id);

public void deletEmployee(long id);


}
