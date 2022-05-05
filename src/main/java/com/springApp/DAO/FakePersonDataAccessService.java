package com.springApp.DAO;

import com.springApp.model.Person;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{

    private static List<Person> DB = new ArrayList<>();

    //POST
    @Override
    public int insertPerson(UUID id, Person person){

            DB.add(
                    new Person(
                            id,
                            person.getName(),
                            person.getEmail(),
                            person.getAddress(),
                            person.getBirthdate(),
                            person.getSalary()
                    )
            );
            return 1;

    }

    //GET
    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    //GET
    @Override
    public Optional<Person> selectPersonById(UUID id){
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    //DELETE
    @Override
    public int deletePersonById(UUID id){
        Optional<Person> personToDelete = selectPersonById(id);

        if(personToDelete.isEmpty()){
            return 0;
        } else{
          DB.remove(personToDelete.get());
        }

        return 1;
    }

    //PUT
    @Override
    public int updatePersonById(UUID id, Person person){
        return selectPersonById(id)
                .map(elem -> {
                    int indexOfPersonToUpdate = DB.indexOf(elem);
                    if(indexOfPersonToUpdate >= 0){
                        DB.set(indexOfPersonToUpdate,
                                new Person(id,
                                        person.getName(),
                                        person.getEmail(),
                                        person.getAddress(),
                                        person.getBirthdate(),
                                        person.getSalary()
                                )
                        );
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }

    //Patch
    @Override
    public int patchPersonById(UUID id, Person person, String updateMask){
        Optional <Person> personOptional = selectPersonById(id);

        if(personOptional.isEmpty()){
            return 0;
        } else {
            Person personToPatch = personOptional.get();
            int index = DB.indexOf(personToPatch);

            if(person.getName() != null)
                personToPatch.setName(person.getName());

            if(person.getEmail() != null)
                personToPatch.setEmail(person.getEmail());

            if(person.getAddress() != null)
                personToPatch.setAddress(person.getAddress());

            if(person.getBirthdate() != null)
                personToPatch.setBirthdate(person.getBirthdate());

            if(person.getSalary() >= 0)
                personToPatch.setSalary(person.getSalary());

            DB.set(index, personToPatch);

        }



        return 1;
    }


}
