package com.agrotep.imp.exp.repository;

import com.agrotep.imp.exp.entity.Person;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
//public interface PersonRepository extends JpaRepository<Person, Integer> {
public class PersonRepository {

    public static final Person SVITLANA = new Person(
            1L,
            "Світлецька Світлана",
            "svitlana",
            "1");
    public static final Person ANTON = new Person(
            2L,
            "Уліцький Антон",
            "anton",
            "1");
    public static final Person OKSANA = new Person(
            3L,
            "Бойко Оксана",
            "oksana",
            "1");
    public static final List<Person> PERSONS = List.of(
            SVITLANA, ANTON, OKSANA
    );

    public static final Map<Person, String> PEOPLE_BACKGROUND_CLASSES = Map.of(
            SVITLANA, "alert-danger",
            ANTON, "alert-success",
            OKSANA, "alert-warning"
    );

    public List<Person> findAll() {
        return PERSONS;
    }

    public Optional<Person> findByName(String name) {
        if (!StringUtils.hasText(name)) {
            return Optional.empty();
        }
        return PERSONS.stream()
                .filter(p -> name.equalsIgnoreCase(p.getName()) || name.equalsIgnoreCase(p.getLogin()))
                .findFirst();
    }
}
