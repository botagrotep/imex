package com.agrotep.imp.exp.entity;


//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//@Entity
@AllArgsConstructor
@Getter
@Setter
public class Person {
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String login;
    private String password;
}
