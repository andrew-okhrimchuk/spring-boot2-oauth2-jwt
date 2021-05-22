package top.okhrimchuk.decompose.service.interfaces;



import top.okhrimchuk.decompose.service.exeption.ServiceExeption;

import java.util.List;

public interface GenericService<T> {
    T add(T t)throws ServiceExeption;
    T update(T newT)throws ServiceExeption;
    List<T> getAll() throws ServiceExeption;
    void delete(int id)throws ServiceExeption;
}
