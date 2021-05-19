package ch.lu.bbzw.backendpersonenverwaltung.controller;

import ch.lu.bbzw.backendpersonenverwaltung.ValidationUtils;
import ch.lu.bbzw.backendpersonenverwaltung.dto.in.*;
import ch.lu.bbzw.backendpersonenverwaltung.dto.out.OutValidationAnswerDto;
import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;
import ch.lu.bbzw.backendpersonenverwaltung.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/person")
public class PersonController{
    private final PersonRepository personRepository;

    @Autowired
    public PersonController(final PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    // Security: User
    @GetMapping("/{id}")
    public InCreatePersonDto getPersonById(@PathVariable String id){
        return personRepository.findById(id).get().toCreatePersonDto();
    }

    // Security: User
    @GetMapping("query/{property}/{value}")
    public List<InQueryPersonDto> query(@PathVariable InSearchByPropertyDto property, @PathVariable String value){
        List<InQueryPersonDto> queriedPersons = null;

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

    // Security: Admin
    @DeleteMapping("/{id}")
    public boolean removePerson(@PathVariable String id){
        return personRepository.removeById(id) != null;
    }

    // Security: Admin
    @PutMapping("/{id}")
    public Set<OutValidationAnswerDto> editPerson(@PathVariable String id, @RequestBody InEditPersonDto editPersonDto){
        Set<OutValidationAnswerDto> validationAnswerDtoSet = ValidationUtils.validateEditPersonDto(editPersonDto);
        if(! validationAnswerDtoSet.isEmpty()){
            return validationAnswerDtoSet;
        }

        Optional<PersonEntity> optionalPersonEntity = personRepository.findById(id);
        if(optionalPersonEntity.isPresent()){
            PersonEntity oldPersonEntity = optionalPersonEntity.get();

            PersonEntity newPersonEntity = editPersonDto.toEntity();
            // These properties are not allowed to be changed by the user
            newPersonEntity.setId(oldPersonEntity.getId());
            newPersonEntity.setUsername(oldPersonEntity.getUsername());

            personRepository.save(newPersonEntity);
        }
        return Collections.emptySet();
    }

    // Security: Admin
    @PostMapping("/")
    public boolean createPerson(@RequestBody InCreatePersonDto createPersonDto){
        personRepository.save(createPersonDto.toEntity());
        return true;
    }

    // Security: User
    @PutMapping("/self")
    public boolean editSelf(@RequestBody InEditSelfDto editSelfDto){
        return false;
    }

}
