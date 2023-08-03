package com.Ban.Mon_Stage.controller;

import com.Ban.Mon_Stage.model.Task;
import com.Ban.Mon_Stage.service.EmployeeService;
import com.Ban.Mon_Stage.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping({"/Task"})
    public ResponseEntity<List<Task>> getAllTask(){
        return new ResponseEntity<>(taskService.getAllTask(), HttpStatus.OK);
    }


    @GetMapping({"/GetTasks","/GetAllTasks"})
    public ResponseEntity<Task> findByTask(@RequestParam() String name  ){
        return new ResponseEntity<>(taskService.findByTask(name), HttpStatus.CREATED);
    }
    @PostMapping("/AddTask")
    public ResponseEntity<Task> AddTask(@RequestBody Task task){
        return new ResponseEntity<>(taskService.AddTask(task),HttpStatus.OK);
    }

    @PutMapping("/UpdateTask/{idtask}")
    // 400 bad request kayn mouchkil f la request
    // pathvariable f url 5asha ttkoun b7al hadif parameter
    public ResponseEntity<Task> UpdateTask(@RequestBody Task task, @PathVariable Long idtask){
        return new ResponseEntity<>(taskService.UpdateDetails(task,idtask),HttpStatus.OK);
    }
    @GetMapping ("/TaskByEmployee")
    public ResponseEntity<Task> TaskByEmployee(@RequestParam Long id){
        return new ResponseEntity<>(taskService.findByEmployeeId(id),HttpStatus.OK);
    }
    @PostMapping("/AddTask_v2")
    public  ResponseEntity<Task> AddTask(@RequestParam String name, @RequestParam Long Id ){
        return new ResponseEntity<>(taskService.SaveTask(name,Id),HttpStatus.CREATED);
    }
    @GetMapping("/Employee/{id_employe}/task")
    public ResponseEntity<Task> gettaskByEmploye(@PathVariable Long id_employe){
        return new ResponseEntity<>(taskService.getTaskbyEmployee(id_employe),HttpStatus.OK);
    }


}
