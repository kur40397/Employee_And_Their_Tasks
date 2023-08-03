package com.Ban.Mon_Stage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_task")
//jpa kayst3mal default constractor to create instance
// l'instance
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // dial mySQL , basic , and incremented incremented By One
    private Long Id;
    private String name;
    // lowercase mzyane luppercase , json
    @JoinColumn(name = "Employee_id")
    // la clé entrangère katreferanci toujours la column id
    @ManyToOne
    private Employee employee;
    // task contient la column clé étrangère qui correspond a la clé société

}
