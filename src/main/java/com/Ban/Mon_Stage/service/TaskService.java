package com.Ban.Mon_Stage.service;

import com.Ban.Mon_Stage.model.Task;

import java.util.List;

public interface TaskService {
    Task addTask(Task task,Long EmployeeId);
    List<Task> getAllTask();
    Task findByTask(String name);
    Task UpdateDetails(Task task, Long idtask);
    Task AddTask(Task task);
    Task findByEmployeeId(Long id);

    Task SaveTask(String nom, Long employee_id);
    Task getTaskbyEmployee(Long employee_id);


}
