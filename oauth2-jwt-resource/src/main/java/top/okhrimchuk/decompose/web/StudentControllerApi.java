package top.okhrimchuk.decompose.web;

import top.okhrimchuk.decompose.model.Student;
import top.okhrimchuk.decompose.service.StudentService;
import top.okhrimchuk.decompose.service.exeption.ServiceExeption;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@Api(value = "Student Controller API", tags = {"Student Controller API"})
public class StudentControllerApi  {

    private final StudentService service;
    public StudentControllerApi(StudentService service) {
        this.service = service;
    }

    @ResponseBody
    @ApiOperation(value = "getAll Student rules for teacher, student and admin", response = Student.class, responseContainer = "List")
    @GetMapping(value = {"/student"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll() {
        try {
            log.debug("getAll.");
            List<Student> list = service.getAll();
            log.debug("getAll. list ={}", list);
            if (list == null || list.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (ServiceExeption ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @ApiOperation(value = "add Student rules for admin", response = Student.class)
    @PutMapping(value = "/student/add")
    public ResponseEntity add(@RequestBody Student student) {
        try {
            Student result = service.add(student);
            return new ResponseEntity(result, HttpStatus.CREATED);
        } catch (ServiceExeption ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @ApiOperation(value = "update Student, rules for teacher and admin", response = Student.class)
    @PostMapping(value = {"/student/edit"})
    public ResponseEntity update(@RequestBody Student student) {
        try {
            log.debug("updateStudent. student ={}", student);
            Student res = service.update(student);
            if (res == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceExeption ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @ApiOperation(value = "deleteById Student,  rules for  admin")
    @DeleteMapping(value = {"/student/delete/{id}"})
    public ResponseEntity deleteById(
            @ApiParam(value = "id of Student", example = "222", required = true)
             @NotNull @PathVariable("id") int id) {
        try {
            log.debug("deleteStudentById. id ={}", id);
            service.delete(id);
        } catch (ServiceExeption ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @ApiOperation(value = "getById Student, rules for student, teacher and admin")
    @GetMapping(value = {"/student/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(
            @ApiParam(value = "id of Student", example = "222", required = true)
            @NotNull @Valid @PathVariable("id") int id) {
        try {
            log.debug("getById. id ={}", id);
            Student student = service.getById(id);
            log.debug("getById. student ={}", student);
            if (student == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>((student), HttpStatus.OK);
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            log.error(errorMessage);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
