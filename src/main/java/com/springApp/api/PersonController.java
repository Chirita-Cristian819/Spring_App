package com.springApp.api;

import com.springApp.service.PersonService;
import com.springApp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    /*~~ the << @Valid >> annotation validates the rules set in the Person class ->

        @NotNull private final UUID id;
        @NotBlank private String name;
        @Email
        private String email;
        @NotBlank
        private String address;
        etc.

    */

    @PostMapping
    public void addPerson(@Valid @RequestBody Person person){
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePerson(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }

    @PatchMapping(path = "{id}")
    public void patchPerson(@PathVariable("id") UUID id,
                            @RequestBody Person personToPatch, @RequestParam String updateMask){
        personService.patchPerson(id, personToPatch, updateMask);
    }

}
