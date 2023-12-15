package com.agrotep.imp.exp.repository;

import com.agrotep.imp.exp.entity.Person;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Repository
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
            SVITLANA, ANTON
    );

    public List<Person> findAll() {
        return PERSONS;
    }

    public Optional<Person> findByName(String name) {
        if (!StringUtils.hasText(name)) {
            return Optional.empty();
        }
        return PERSONS.stream()
                .filter(p -> name.equalsIgnoreCase(p.getName()))
                .findFirst();
    }
}
