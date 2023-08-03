package com.Ban.Mon_Stage.service;

import com.Ban.Mon_Stage.Repository.EmployeeRepository;
import com.Ban.Mon_Stage.Repository.TaskRepository;
import com.Ban.Mon_Stage.model.Employee;
import com.Ban.Mon_Stage.model.Task;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired // ida madrtiahch 5ask thandiliha rasek
    private TaskRepository taskRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Task addTask(Task task, Long EmployeeId) {
        // 7ta idar l'appel dial had la fonction fwast kat5dem
        Employee employee=employeeRepository.findById(EmployeeId).orElseThrow(()->new EntityNotFoundException("Entity no found"));
         task.setEmployee(employee);
         // 5asek 7ta tmappi 3ad sta3mal les getters et les setters diaoulek
        // entity hiya hadik la ligne li f db
        return taskRepository.save(task);
    }



    @Override
    public List<Task> getAllTask() {

        return taskRepository.findAll();
    }

    @Override
    public Task findByTask(String name) {

        return taskRepository.findByName(name);
        // kayreturni lik dak la parameter li sayviti
    }

    @Override
    // hna 5asak tsayvih f db
    // hna 3andna  rir la copie dial task
    public Task UpdateDetails(Task task, Long idtask) {
        Task OldTask=taskRepository.findById(idtask).orElseThrow(()->new EntityNotFoundException("id"+idtask+"not found"));
        if(employeeRepository.existsById(task.getEmployee().getId())){
            task.setId(OldTask.getId());
            return taskRepository.save(task);
        }
        throw new EntityNotFoundException("id"+task.getEmployee().getId()+"not found");
    }

    // kaya5od rir id 7it lazy
    // When you load an entity (e.g., a Task object), the related entities
    // (e.g., the associated Employee object) are not loaded immediately.
    @Override
    public Task AddTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task findByEmployeeId(Long id) {
        return taskRepository.findByEmployeeId(id);
    }

    @Override
    public Task SaveTask(String nom, Long employee_id) {
        return taskRepository.save(nom,employee_id);
    }

    @Override
    public Task getTaskbyEmployee(Long employee_id) {
        if(taskRepository.existsById(employee_id)){
            throw new EntityNotFoundException("id"+ employee_id +"not found boy !! ");
        }
        Task task=taskRepository.findByEmployeeId(employee_id);
        return task;
    }

}
