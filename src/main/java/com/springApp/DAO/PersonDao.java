package com.springApp.DAO;

import com.springApp.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    //method to insert a person (POST)
    int insertPerson(UUID id, Person person);


    //default method to insert a person
    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    //retrieve all people (GET)
    List<Person> selectAllPeople();

    //select person by ID (GET)
    Optional<Person> selectPersonById(UUID id);

    //delete a person (DELETE)
    int deletePersonById(UUID id);

    //update a person (PUT)
    int updatePersonById(UUID id, Person person);

    //patch a person (PATCH)
    int patchPersonById(UUID id, Person person, String updateMask);

}
