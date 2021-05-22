package top.okhrimchuk.decompose.service.Validator;

import top.okhrimchuk.decompose.exeption.DaoExeption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CourseValidator implements Validator <String> {

    public void isValid(String name) {
        if (name == null || name == "") {
            log.error("Name cant be null ! name = {}"  , name);
            throw new DaoExeption("Name cant be null !" , null);
        }
        log.debug("isValid is OK, name = {}", name);
    }
}
