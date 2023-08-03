package com.Ban.Mon_Stage.Repository;

import com.Ban.Mon_Stage.model.Employee;
import com.Ban.Mon_Stage.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
   Task findByName(String name);
   Task findByEmployeeId(Long id);
   @Query(value = "INSERT INTO tbl_task (name, employee_id) VALUES (:nom,:employee_id)",nativeQuery = true)
   Task save(String nom, Long  employee_id);



}
