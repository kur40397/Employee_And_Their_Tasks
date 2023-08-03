package com.Ban.Mon_Stage.model;

import com.Ban.Mon_Stage.Request.EmployeeRequest;
import jakarta.persistence.*; // fiha des interfaces et des annotations li kay3awnouna pour le mappage
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
//import java.util.Date le type Data b7al TimeStamp

// hibernate kaysta3mal @table bach imapi la class m3a la base de donnée
@Getter
@Setter
@ToString
@Entity(name = "tbl_employe")   // katpresenti la table f la db et smitha
@Table(name = "tbl_employe",schema = "Employees") // katspicifi le nom dial la table f la db
// model howa li kaywarina chkl li bih radin nsifto data mn contrl l view
//@JsonIgnoreProperties(value = {"name", "updateAt"},allowSetters  = true)
//allowSetters=true , mzyana f la serilization
// allowGetters=true , deseriliazation et machi serilization

public class Employee {
   // @Getter(AccessLevel.NONE) to disable getter/setter
   // generate on a specific field
   //  @JsonProperty("")
   //@JsonProperty annotation that is used to change the property name in serialized JSON
   @Id // katgol biana had le champs rah primary class
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   // jpa chooses the appropriate strategy based capacity of database
   // INDENTITY primary key pour la db mysql incremting by one
   // basic , facile , makatzitch 3lik tamara , AI kaytgenera mn db itself
   // SEQUENCE pour dakchi dial oracle , wa7d objet sequence li kaydir tkalaf b l'incrementation
   // chi objet dial tzaf f db  the database fetches the next value from the sequence and assigns it as the primary key for the new record

   private Long id;


   @NotNull(message = "Name should not be null") // darouriya mnla5ar
  // @NotNull checks if the value is not null.
   //@NotEmpty checks if the value is not null and has a length greater than zero.
   //@NotBlank checks if the value is not null, has a length greater than zero, 5asek tkoun katb chi 7aja 3al akal une lettre.
   // empty rah value mais length dialha 0 ama null rah makaych value
   // null : name katpointi 3la null ama empty katpointi 3la wa7d objet / string li fih 7ta chi character
   // MethodArgumentNotValidException
   // la serialization bin la db et api et api bin user
   private String nom;

   private Long age=0L;
   //@JsonIgnore
   private String location;

   @Email(message = "Please enter the valid email adresse")
   private String email;
   @NotNull(message = "Name should not be null")
   private String departement;
   @CreationTimestamp // kaymchi nichane la base de donnée et kaymodifi
   // nullable maymkanch liya tkoun empty
   // maymkch tbadal
   @Temporal(TemporalType.TIMESTAMP) // presenti liya TIMESTAMP f la base de donnée
   private Timestamp createdAt;

   //A Timestamp object contains both a date and a time, down to the nanosecond precision.
   //ida briti dakchi precise

   @UpdateTimestamp
   private Date updateAt;
   @OneToMany(mappedBy = "employee")
   // katgol bli had la class rah reference dialha rah kayna f employee
   // la clé etrangère rah kayna f jiha mo3akisa li biha radi tkoum b la relation



   // katdar l'entity li mafihach foreign key
   private List<Task> task;
}




// json used to store and exchange the data
// serialization and communication
// representation dial objet an entity


// hibernate validator . It checks the state of the
// object and its properties to ensure they meet the specified criteria or rules.
