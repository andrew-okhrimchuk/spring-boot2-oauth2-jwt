package com.marcosbarbero.lab.sec.oauth.jwt.ds.service.Validator;

public interface Validator <T> {
    void isValid(T t);
}
