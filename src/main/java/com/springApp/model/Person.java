package com.springApp.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.validation.constraints.*;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(value = "Person")
public class Person {

    @Id
    private final UUID id;

    @Column(value = "name")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotNull
    @Column(value = "email")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+.[a-z]{2,4}$")
    private String email;

    @Column(value = "address")
    @NotBlank
    private String address;

    @Column(value = "birthdate")
    @Pattern(regexp = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$")
    private String birthdate;

    @PositiveOrZero
    @Column(value = "salary")
    private double salary;

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("email") String email,
                  @JsonProperty("address")String address,
                  @JsonProperty("birthdate") String birthdate,
                  @JsonProperty("salary") double salary){
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.birthdate = birthdate;
        this.salary = salary;
    }

;
}
