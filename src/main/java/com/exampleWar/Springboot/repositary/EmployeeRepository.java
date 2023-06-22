package com.exampleWar.Springboot.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exampleWar.Springboot.model.Employee;


//In Spring data jpa internally contains @Repository and @Transactional so if we dont declare these annotation then also it will consider internally
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
