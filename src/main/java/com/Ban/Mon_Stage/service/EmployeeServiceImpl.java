package com.Ban.Mon_Stage.service;

import com.Ban.Mon_Stage.Repository.EmployeeRepository;
import com.Ban.Mon_Stage.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = false )
// only RuntimeException and Error trigger a rollback
// readOnly = true  , les methods imkan lihou idirou les operations get chi la5or la
// Any attempt to perform write or update operations will result in an exception being thrown.

// @Transactional(rollbackFor = {SQLException.class},
//            noRollbackFor = {EntityNotFoundException.class})
// hna katspecifie chanouhma les exception li radi provikiou rollback ou hadouk li la
public class EmployeeServiceImpl implements EmployeeService {
     @Autowired
     private EmployeeRepository employeeRepository;
    @Override
    public Map<String, Object> getEmployees(int page, int size) {
        /*
        It is generally a good practice to refer to the method's return type or parameter as a
        Pageable object rather than a specific implementation like PageRequest.
         */
        Pageable pageable= PageRequest.of(page,size, Sort.Direction.DESC,"id","Nom");
        // PageRequest katemplimenti pageable
        // PageRequest contien Page & size
        //********************************************//
        // Page :contient
        /*
        * Page Number: l numero dial la page actuelle
        * Page Size: size dial each page
        * Total Pages: le nombre total de page
        * data li radi la page actuelle & data kolha f db
        * */
        Map<String, Object> response = new HashMap<>();

        response.put("page",pageable.getPageNumber());
        response.put("size",pageable.getPageSize());
        Page<Employee> MyPage=employeeRepository.findAll(pageable);

        if(MyPage.hasContent()) {// bach t3raf wech kayle contenu f had la page wla la
            response.put("total page", MyPage.getTotalPages());
            response.put("total all elements", MyPage.getTotalElements());
            response.put("total of each page ", MyPage.getNumber());
            response.put("all the mage", MyPage);
        }

        return response;
        //List : fiha les objects kamlin li 3andna f db
        // getContent() bach treturni  une list blast page<Employee>

    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getSingleEmployee(Long id) {
        Optional<Employee> emp=employeeRepository.findById(id);
        // Optional kat7mik mn null ki radi tprovoki lik exception
            return emp.orElseThrow(()->new RuntimeException("Employee is not found for the id"));


    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        // delete makaritrunich un object save & find
        // In Banch delete kolchi f5tra machi wa7da bwa7da
    }

    @Override
    public Employee updateEmployee( Employee employee) {
        // object ida kan3ando id katkayremplasiouh bwa7da 5or
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.findByNom(name,Sort.by(Sort.Direction.ASC,"createdAt"));
        // f la recherche un espace kat7sbo lik b7al 0 espace
    }

    @Override
    public List<Employee> getEmployeesByNameAndLocation(String name, String location) {
        return employeeRepository.findByNomAndLocation(name,location);
    }

    @Override
    public List<Employee> getEmployeesByKeyword(String name) {
        Sort sort=Sort.by(Sort.Direction.DESC,"id");
        // object sort , order by Id nad Decroissant
        return employeeRepository.findByNom(name);
    }
    @Override
    public List<Employee> getEmployeesByDepartement(String departement,int page,int size){
        Pageable pageable=PageRequest.of(page,size, Sort.Direction.DESC,"id");
        // darouri f sort tdir la direction and lproperties
        return employeeRepository.findByDepartement(departement,pageable);
    }

    @Override
    public List<Employee> getEmployeesSorted(Sort sort) {
        return employeeRepository.findAll(sort);
    }

    @Override
    public List<Employee> getEmployeesMultiSort(Sort sort) {
        return employeeRepository.findAll(sort);
    }

    @Override
    public List<Employee> getEmployeesByNameORAge(String name, Long age) {
        return employeeRepository.getEmployeeByNameOrAge(name,age);
    }

    /**
     * @param Name
     * @param size
     * @param page
     * @return Slice<Employee>
     */
    @Override
    public Slice<Employee> findFirstByName(String Name, int page, int size){
      Pageable p=PageRequest.of(page,size);

      return  employeeRepository.findByNom(Name,p);
    }

    @Override
    public Integer deleteByEmployeeName(String name) {

        return employeeRepository.deleteEmployeeByNom(name);
                // nom dial les object 5ashoum ikounou mini f lbadya
    }
}
