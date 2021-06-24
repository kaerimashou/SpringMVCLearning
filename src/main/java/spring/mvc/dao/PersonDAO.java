package spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.mvc.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    private static int PEOPLE_COUNT;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person",new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person VALUES(1,?,?,?)",
                person.getName(),person.getAge(),person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name=?,age=?,email=? WHERE id=?",
                updatedPerson.getName(),updatedPerson.getAge(),updatedPerson.getEmail(),id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE from PERSON WHERE id=?",id);
    }
}
