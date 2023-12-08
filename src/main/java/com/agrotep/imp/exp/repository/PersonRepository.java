package com.agrotep.imp.exp.repository;

import com.agrotep.imp.exp.entity.Person;
//import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//public interface PersonRepository extends JpaRepository<Person, Integer> {
public class PersonRepository {

    public static final Person SVITLANA = new Person(
            1L,
            "Світлецька Світлана",
            "svitlana",
            "svitlana");
    public static final Person ANTON = new Person(
            2L,
            "Антон Уліцький",
            "anton",
            "anton"
    );
    public static final List<Person> PERSONS = List.of(
            SVITLANA//, ANTON
    );

    public List<Person> findAll() {
        return PERSONS;
    }
}
