package com.Ban.Mon_Stage.Repository;


import com.Ban.Mon_Stage.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// data access
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    // jpaRepository katextendi mn So it contains API for basic CRUD operations and also API for pagination and sorting.
    // query method homa les methods par défaut
    // 1* query method kaybda b findBy
    // 2* field li bih bari tdir la recherche 5ashoum ikounou majiscule
    // 3* ida briti tdir multiple methods 5asek tdir 'And'
    // public List<Student> findByNameAndRollNumber(String name,String rollNumber)
    // ida briti t7aded  ch7al mn wa7d findFirst3ByName(String name)
    // 5asna nsta3mlou entity fieldName machi la column f db


    List<Employee> findByNom(String name,Sort sort);
    List<Employee> findByNomAndLocation(String name,String location);
    // jpa howa li radi iconverti liya querymethod l sql  request
    List<Employee> findByNom(String key);
    //findByNameContainingOrderByIdDesc
    // sorting:
    // 1 sorting b query method nichane OrderByNameDesc
    // 2 t7ot l'objet sort f parameter (rir findAll) & query method dialek
    // findAll(Pageable) return Page ama query method  dir li briti
    // 3 dtir pagination & Sort f nfs la9t
    // Page<Passenger> page = repository.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "seatNumber")));
    // mn a7sen pagination
    // findAll(Pageable pageable) and findAll(Sort sort)

    List<Employee> findByDepartement(String department , Pageable pageable);



    // @Params kanst3mlouh ida method attribute m5talfa 3la l query kat5lina nmappiou bin the parameter f lquery and methodquery

    // :variable






   @Query(value = "SELECT * FROM tbl_employe WHERE nom=:name OR age=:age",nativeQuery = true)
    List<Employee> getEmployeeByNameOrAge(String name, Long age);

    // findBy getEmployeesBy
            // mnin katzid query kat3tik la performance
            /*
            ida kant 3andna une jointure
            des conditions complexes
            performance ktr
            bzaf dial les attributes
             */
    Slice<Employee> findByNom(String Name, Pageable pageable);
    // native query smiya dial les columns
    // non native query smiya dial les entity

    @Modifying // katgol spring ana had query dial la modification f db
    @Query("DELETE FROM tbl_employe WHERE nom=:name") // smiya dial les objects 7it hada jpql
    Integer deleteEmployeeByNom(String name);
    // Integer ch7al mn element deleta
    // Integer ida returnat null value
    // exception imlkan liya tretuna f Interger

    // find By Id kayna par defaut



}
