package com.marcosbarbero.lab.sec.oauth.jwt.service.exeption;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class ServiceExeption extends Exception  {
    private  Exception e;
    private  String message;

    public ServiceExeption(String msg, Exception e) {
        this.e = e;
        this.message = msg;
    }
}
