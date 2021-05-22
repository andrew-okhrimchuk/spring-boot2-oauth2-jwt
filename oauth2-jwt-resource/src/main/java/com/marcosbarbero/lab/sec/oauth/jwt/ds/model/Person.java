package com.marcosbarbero.lab.sec.oauth.jwt.ds.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract   class Person implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @NotBlank
    @Column(name = "first_name")
    @Size(min = 1, max = 50)
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    @Size(min = 1, max = 50)
    private String lastName;

    protected Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
