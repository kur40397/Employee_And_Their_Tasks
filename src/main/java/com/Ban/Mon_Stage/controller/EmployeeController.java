package com.Ban.Mon_Stage.controller;

import com.Ban.Mon_Stage.model.Employee;
import com.Ban.Mon_Stage.model.Task;
import com.Ban.Mon_Stage.service.EmployeeService;
import com.Ban.Mon_Stage.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@Controller  ida knti bari treturni view MVC
//une kadir @controller la class kanconfiguriouha bach thandli lhttp
@RestController // @Controller + @ResponseBody
public class EmployeeController {
       @Autowired
       private EmployeeService eService;
       @Autowired
       private TaskService taskService;

      //@PropertySource is an annotation in Spring used to specify the location
      // of external property files that contain configuration settings for a Spring application
       @Value("${spring.application.name:mon application }")
       // injecti bzaf dial les types
       // # ida knti bari tdir un traitement
       private String appName;
       @Value("${application.version:version 1 }")
       private String appVersion;
       @GetMapping("/version")
       public String getAppDetails(){
           return appVersion+" - "+appName;
       }

       // endpoint == url
       //@RequestMapping(value = "/employees",method = RequestMethod.GET)
       @GetMapping("/employees")
       // request methode
       // katmappi une requetes b methode http et katspisifi
       //@ResponseBody
       // The @RequestBody return dial un method controller radi ikoun body f la requet http

       public ResponseEntity<Map<String, Object>> getEmployees(@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "3") Integer size){
           // f body darouri 5as ikoun type generic
           // l'ordre machi important ==> kolchi radi idoze mzyane
           return new ResponseEntity<>(eService.getEmployees(page, size), HttpStatus.OK);
           // HttpStatus wa7d enum li fih representation dial various HTTP status
           // Katkriyi l'instance dial ResponseEntity li biha kanpresentiou la reponse http
       }
       @GetMapping("/employee/{id}")
       //@PathVariable extract the dynamic part
       public Employee getEmployees(@PathVariable("id") Long id ){
           return eService.getSingleEmployee(id);
       }

       @PostMapping(value = "/employees")
       // will have the content type of JSON
       // @valid kay5dem m3a @RequestBody , 7ta katw9a3 deserialization 3ad @valid kat5dem
       public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){
           // object lower type upper

           return new ResponseEntity<>(eService.saveEmployee(employee), HttpStatus.CREATED);
       }
       @DeleteMapping("/employees")
       public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam("id") Long id){
           eService.deleteEmployee(id);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           // f delete kandirou NO_CONTENT
       }
       @PutMapping("/employees/{id}")
       public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
         employee.setId(id);
         return new ResponseEntity<>(eService.updateEmployee(employee),HttpStatus.OK);
       }
       @GetMapping("/employees/filterByName")
       public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name){
           return new ResponseEntity<>(eService.getEmployeesByName(name),HttpStatus.OK);

       }
    @GetMapping("/employees/filterByNameAndLocation")
    // parameter li f l'url 5asha tkoun b7al li f li method li m3a @Request
    public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@RequestParam String name,@RequestParam String location){
        return new ResponseEntity<>(eService.getEmployeesByNameAndLocation(name,location),HttpStatus.OK);
        // generic type kaydetecta automatiquement
    }
    // ida jbti chi method makaynach katdir lik 405 not allowes
    @GetMapping("/employees/filterByKeyword")
    public ResponseEntity<List<Employee>> getEmployeesByKeyword(@RequestParam String name){
        return new ResponseEntity<>(eService.getEmployeesByKeyword(name),HttpStatus.OK);
    // In Java and Spring Framework, a string containing only white spaces is considered a non-empty string with a zero length
    }
    @GetMapping("/employees/filterByDepartement/{departement}/{page}/{size}")
    // smiya f controller camle case et 7ta f repository
    // variable mininscule
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable String departement ,@PathVariable Integer page, @PathVariable Integer size ){
           // makaych valeur par defaut f @PathVariable
           return new ResponseEntity<>(eService.getEmployeesByDepartement(departement,page,size),HttpStatus.OK);
    }
    @GetMapping("/employees/employeesSorted")
    public ResponseEntity<List<Employee>> getEmployeesSorted(@RequestParam(defaultValue = "id") String sort){
           // sort
           Sort  s = Sort.by(sort); // darouri les properties idarou
           return new ResponseEntity<>(eService.getEmployeesSorted(s),HttpStatus.OK);
    }
    @GetMapping("/employees/employeesMultiSorted")
    public ResponseEntity<List<Employee>> getEmployeesMultiSort(@RequestParam(defaultValue = "id") String sort1,@RequestParam(defaultValue = "name") String sort2) {
        Sort s = Sort.by(sort1).and(Sort.by(sort2)); // deux objets sorts kanjm3ohoum f object wa7d
        return new ResponseEntity<>(eService.getEmployeesMultiSort(s),HttpStatus.OK);
        //  HttpStatus.OK darouriya bach luser ifhem fin wsal la requete dialou
    }
    // darouri f request param 5asek tdir defaultvalue f les variables dial sorting and pagination

    // f sort "" & " " homa asra wa7din & minisucle hiya lawla



    // kandirou generic type f ResponseEntity<T> bach l'user wla UI li katsna had la reponse anahou hadi tjih reponsebody b type T
    // ikoun 3arf chnahiya data li jayah mn l'API
    @GetMapping("/employees/getEmployeesByNameORAge")
    public ResponseEntity<List<Employee>> getEmployeesByNameORAge(String name , Long age){
           return new ResponseEntity<>(eService.getEmployeesByNameORAge(name,age),HttpStatus.OK);
    }
    @GetMapping("/employees/findFirstByName")
    // query lawal kayji f paramtre de variable howa lawal  wa5a ikounou m5talfin
    public ResponseEntity<Slice<Employee>> findFirstByName(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size){
           return new ResponseEntity<>(eService.findFirstByName("badr",page,size),HttpStatus.OK);
    }
    @DeleteMapping ("/employees/delete/{name}")
    public ResponseEntity<String> deleteEmployeesByName(@PathVariable() String name){
        return new ResponseEntity<>(eService.deleteByEmployeeName(name)+"deleted",HttpStatus.OK);
    }
}
