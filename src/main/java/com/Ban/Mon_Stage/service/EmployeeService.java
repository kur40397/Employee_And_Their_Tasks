package com.Ban.Mon_Stage.service;

import com.Ban.Mon_Stage.model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface EmployeeService {
    Map<String, Object> getEmployees(int page, int size);
    Employee saveEmployee(Employee employee);
    Employee getSingleEmployee(Long id);
    void deleteEmployee(Long id);
    Employee updateEmployee(Employee employee);
    List<Employee> getEmployeesByName(String name);
    List<Employee> getEmployeesByNameAndLocation(String name, String location);
    List<Employee> getEmployeesByKeyword(String name);
    List<Employee> getEmployeesByDepartement(String departement , int page,int size);
    List<Employee> getEmployeesSorted(Sort sort);
    List<Employee> getEmployeesMultiSort(Sort sort);
    List <Employee> getEmployeesByNameORAge(String name, Long age);

    Slice<Employee> findFirstByName(String Name, int size, int page);
    // les interfaces kandirou lihoum get
    Integer deleteByEmployeeName(String name);



}
