package com.marcosbarbero.lab.sec.oauth.jwt.ds.service;

import com.marcosbarbero.lab.sec.oauth.jwt.ds.DataJPA.StudentsJPARepository;
import com.marcosbarbero.lab.sec.oauth.jwt.ds.exeption.DaoExeption;
import com.marcosbarbero.lab.sec.oauth.jwt.ds.model.Student;
import com.marcosbarbero.lab.sec.oauth.jwt.ds.service.Validator.Validator;
import com.marcosbarbero.lab.sec.oauth.jwt.ds.service.exeption.ServiceExeption;
import com.marcosbarbero.lab.sec.oauth.jwt.ds.service.interfaces.StudentsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StudentService implements StudentsService {
    private final StudentsJPARepository repository;
    private final Environment env;
    private final Validator validator;

    @Override
    public Student add(String firstName, String lastName, String contract, String description) throws ServiceExeption {
        log.debug("Start add. firstName = {}, lastName = {} ,contract ={}", firstName, lastName, contract);
        try {
            validator.isValid(firstName);
            validator.isValid(lastName);
            return repository.save(Student.builder().firstName(firstName).lastName(lastName).contract(contract).description(description).build());
        } catch (DaoExeption e) {
            log.error("{}, {}", env.getProperty("ADD_ERROR_MESSAGE_STUDENT"), e.getMessage());
            throw new ServiceExeption(e.getMessage(), e);
        }
    }

    @Override
    public Student add(Student student) throws ServiceExeption {
        log.debug("Start add. student ={}", student);
        try {
            validator.isValid(student.getFirstName());
            validator.isValid(student.getLastName());
            return repository.save(student);
        } catch (DaoExeption e) {
            log.error("{}, {}", env.getProperty("ADD_ERROR_MESSAGE_STUDENT"), e.getMessage());
            throw new ServiceExeption(e.getMessage(), e);
        }
    }

    public Student getById(int id) throws ServiceExeption {
        log.debug("Start getById. id={}", id);
        try {
            return repository.findById(id);
        } catch (DaoExeption e) {
            log.error("{}, {}", env.getProperty("GET_ERROR_MESSAGE_STUDENT"), e.getMessage());
            throw new ServiceExeption(e.getMessage(), e);
        }
    }

    @Override
    public List<Student> getAll() throws ServiceExeption {
        log.debug("Start getAll. ");
        try {
            return repository.findAll();
        } catch (DaoExeption e) {
            log.error("{}, {}", env.getProperty("GET_ALL_ERROR_MESSAGE_STUDENT"), e.getMessage());
            throw new ServiceExeption(e.getMessage(), e);
        }
    }

    @Override
    public Student update(Student studentNew) throws ServiceExeption {
        log.debug("Start update. studentNew={}", studentNew);
        try {
            validator.isValid(studentNew.getFirstName());
            validator.isValid(studentNew.getLastName());

            return repository.save(studentNew);
        } catch (DaoExeption e) {
            log.error("{}, {}", env.getProperty("UPDATE_ERROR_MESSAGE_STUDENT"), e.getMessage());
            throw new ServiceExeption(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) throws ServiceExeption {
        log.debug("Start delete. id ={}", id);
        try {
            repository.deleteById(id);
        } catch (DaoExeption e) {
            log.error("{}, {}", env.getProperty("DELETE_ERROR_MESSAGE_STUDENT"), e.getMessage());
            throw new ServiceExeption(e.getMessage(), e);
        }
    }

    public Page<Student> findPaginated(Pageable pageable) {
        log.debug("Start findPaginated. pageable = {}", pageable);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Student> list = Collections.emptyList();
        int total = 0;
        try {
            list = getAll();
            if (list !=null ) {
                total = list.size();
            }
            else list = Collections.emptyList();
        } catch (ServiceExeption serviceExeption) {
            serviceExeption.printStackTrace();
            list = Collections.emptyList();
        }

        if (list.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, list.size());
            list = list.subList(startItem, toIndex);
        }
        log.debug("End findPaginated. PageRequest.of(currentPage, pageSize) = {}, total = {}", PageRequest.of(currentPage, pageSize), total);
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), total);
    }
}
