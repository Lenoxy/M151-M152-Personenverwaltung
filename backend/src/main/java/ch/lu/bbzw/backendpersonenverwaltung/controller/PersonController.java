package ch.lu.bbzw.backendpersonenverwaltung.controller;

import ch.lu.bbzw.backendpersonenverwaltung.controller.dto.QueryPersonDto;
import ch.lu.bbzw.backendpersonenverwaltung.controller.dto.SearchByProperty;
import ch.lu.bbzw.backendpersonenverwaltung.controller.dto.SinglePersonDto;
import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;
import ch.lu.bbzw.backendpersonenverwaltung.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/person")
public class PersonController{
    private final PersonRepository personRepository;

    @Autowired
    public PersonController(final PersonRepository personRepository){
        this.personRepository = personRepository;
    }


    @GetMapping("/{id}")
    public SinglePersonDto getPersonById(@PathVariable String id){
        return personRepository.findById(id).get().toSinglePersonDto();
    }

    @GetMapping("query/{property}/{value}")
    public List<QueryPersonDto> query(@PathVariable SearchByProperty property, @PathVariable String value){
        List<QueryPersonDto> queriedPersons = null;

        switch(property){
            case id:
                queriedPersons = Collections.singletonList(personRepository.findById(value).get().toQueryPersonDto());
                break;
            case firstname:
                queriedPersons = personRepository.findByFirstnameIgnoreCase(value).stream().map(PersonEntity::toQueryPersonDto).collect(Collectors.toList());
                break;
            case lastname:
                queriedPersons = personRepository.findByLastnameIgnoreCase(value).stream().map(PersonEntity::toQueryPersonDto).collect(Collectors.toList());
                break;
            case email:
                queriedPersons = personRepository.findByEmailIgnoreCase(value).stream().map(PersonEntity::toQueryPersonDto).collect(Collectors.toList());
                break;
        }
        return queriedPersons;
    }

    @DeleteMapping("/{id}")
    public boolean removePerson(@PathVariable String id){
        return personRepository.removeById(id) != null;
    }

    @PutMapping("/{id}")
    public boolean editPerson(@PathVariable String id, @RequestBody SinglePersonDto singlePersonDto){
        return false;
    }

    @PostMapping("/")
    public boolean createPerson(@RequestBody SinglePersonDto singlePersonDto){
        personRepository.save(singlePersonDto.toEntity());
        return true;
    }

    @PutMapping("/self")
    public boolean editSelf(@RequestBody SinglePersonDto singlePersonDto){
        return false;
    }

}
