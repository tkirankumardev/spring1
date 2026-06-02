package com.tkkcode.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "name can't be null")
    @Size(min=4, message = "size must more than 4")
    private String name;

    @NotBlank(message = "last name can't be null")
    @Size(min=4, message = "size must more than 4")
    private String last_name;
}
