package com.marcosbarbero.lab.sec.oauth.jwt.ds.service.interfaces;


import com.marcosbarbero.lab.sec.oauth.jwt.ds.model.Student;
import com.marcosbarbero.lab.sec.oauth.jwt.ds.service.exeption.ServiceExeption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentsService extends GenericService <Student> {
    Student add(String firstName, String lastName, String contract, String description) throws ServiceExeption;
    Student add(Student student) throws ServiceExeption;
    Student getById(int id) throws ServiceExeption;
    List<Student> getAll() throws ServiceExeption;
    Student update(Student studentNew) throws ServiceExeption;
    void delete(int id) throws ServiceExeption;
    Page<Student> findPaginated(Pageable pageable);
}
