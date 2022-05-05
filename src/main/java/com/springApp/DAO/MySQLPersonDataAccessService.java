package com.springApp.DAO;

import com.springApp.utils.UUIDAdapter;
import com.springApp.datasources.MySQLDatasource;
import com.springApp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository("MySQLDao")
public class MySQLPersonDataAccessService implements PersonDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MySQLPersonDataAccessService(MySQLDatasource sourceConfig){
        this.jdbcTemplate = new JdbcTemplate(sourceConfig.dataSource());
        }

    //POST
    @Override
    public int insertPerson(UUID id, Person person){

        String sql = "INSERT into Person(id, name, email, address, birthdate, salary) VALUES (UUID_TO_BIN(?), ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, id.toString(),
                person.getName(),
                person.getEmail(),
                person.getAddress(),
                person.getBirthdate(),
                person.getSalary());


    }

    //GET
    @Override
    public List<Person> selectAllPeople() {
        String sql = "SELECT id as id, name, email, address, birthdate, salary FROM Person";

        List<Person> listOfUsers = jdbcTemplate.query(sql, (resultSet, i) -> {

            UUID id = UUIDAdapter.getUUIDFromBytes(resultSet.getBytes("id"));
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            String birthdate = resultSet.getString("birthdate");
            double salary = resultSet.getDouble("salary");
            return new Person(id, name, email, address, birthdate, salary);
        });

        return listOfUsers;
    }

    //GET
    @Override
    public Optional<Person> selectPersonById(UUID id){
        String sql = "SELECT * FROM Person WHERE id = UUID_TO_BIN(?)";

        Person user= jdbcTemplate.queryForObject(sql, (resultSet, i) -> {

            UUID uid = id;
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            String birthdate = resultSet.getString("birthdate");
            double salary = resultSet.getDouble("salary");
            return new Person(uid, name, email, address, birthdate, salary);
        }, id.toString());

        return Optional.ofNullable(user);
    }

    //DELETE
    @Override
    public int deletePersonById(UUID id){
        String sql = "DELETE FROM Person WHERE id = UUID_TO_BIN(?)";
        return jdbcTemplate.update(sql, id.toString());
    }

    //PUT
    @Override
    public int updatePersonById(UUID id, Person person){
        String sql = "UPDATE Person SET name = ?, email = ?, address = ?, birthdate = ?, salary = ? WHERE id = UUID_TO_BIN(?)";
        //noinspection RedundantArrayCreation
        return jdbcTemplate.update(sql, new Object[]
                {
                person.getName(),
                person.getEmail(),
                person.getAddress(),
                person.getBirthdate(),
                person.getSalary(),
                id.toString()
                }
        );
    }

    //PATCH - to be implemented
    public int patchPersonById(UUID uuid, Person person, String updateMask) {

        List<String> fieldsToUpdate = new ArrayList(Arrays.asList(updateMask.replaceAll("\\s+","").split(",")));
        String joined = fieldsToUpdate.stream().collect(Collectors.joining(" = ?, "));

        List<Object> values = new ArrayList<>();

        for (String field : fieldsToUpdate) {
            if (field.equals("name")) {
                values.add(person.getName());
            }

            if (field.equals("email")) {
                values.add(person.getEmail());
            }

            if (field.equals("address")) {
                values.add(person.getAddress());
            }

            if (field.equals("birthdate")) {
                values.add(person.getBirthdate());
            }

            if (field.equals("salary")) {
                values.add(person.getSalary());
            }
        }

        values.add(uuid.toString());

        String sql = "UPDATE Person SET " + joined + " = ? WHERE id = UUID_TO_BIN(?)";

        return jdbcTemplate.update(sql, values.toArray());

    }

    public static void main(String[] args){

    }

}
