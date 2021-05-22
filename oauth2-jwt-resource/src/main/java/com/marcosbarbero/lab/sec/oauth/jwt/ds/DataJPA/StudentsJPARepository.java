package com.marcosbarbero.lab.sec.oauth.jwt.ds.DataJPA;


import com.marcosbarbero.lab.sec.oauth.jwt.ds.model.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentsJPARepository extends CrudRepository<Student, Integer> {
    @Modifying(clearAutomatically = true)
    Student save(Student student);

    Student findById(int id);
    List<Student> findAll();

    @Modifying(clearAutomatically = true)
    void deleteById(int id);
}
