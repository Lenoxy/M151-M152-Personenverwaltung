package ch.lu.bbzw.backendpersonenverwaltung.controller;

import ch.lu.bbzw.backendpersonenverwaltung.ValidationUtils;
import ch.lu.bbzw.backendpersonenverwaltung.dto.httpException.NotAuthorizedException;
import ch.lu.bbzw.backendpersonenverwaltung.dto.in.*;
import ch.lu.bbzw.backendpersonenverwaltung.dto.out.OutValidationAnswerDto;
import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;
import ch.lu.bbzw.backendpersonenverwaltung.repository.PersonRepository;
import ch.lu.bbzw.backendpersonenverwaltung.service.JwtService;
import ch.lu.bbzw.backendpersonenverwaltung.stereotypes.ProtectedForRole;
import ch.lu.bbzw.backendpersonenverwaltung.stereotypes.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    private final JwtService jwtService;

    @Autowired
    public PersonController(final PersonRepository personRepository, final JwtService jwtService){
        this.personRepository = personRepository;
        this.jwtService = jwtService;
    }

    @ProtectedForRole(UserRole.USER)
    @GetMapping("/{id}")
    public InCreatePersonDto getPersonById(@PathVariable String id){
        return personRepository.findById(id).get().toCreatePersonDto();
    }

    // TODO UNUSED
    @ProtectedForRole(UserRole.USER)
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

    @ProtectedForRole(UserRole.USER)
    @GetMapping("/query")
    public List<InQueryPersonDto> queryAll(){
        return personRepository.findAll().stream().map(PersonEntity::toQueryPersonDto).collect(Collectors.toList());
    }

    @ProtectedForRole(UserRole.ADMIN)
    @DeleteMapping("/{id}")
    public boolean removePerson(
            @PathVariable String id,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt
    ){
        if(! jwtService.getIdFromClaim(jwt).equals(id)){
            return personRepository.removeById(id) != null;
        }else{
            throw new NotAuthorizedException();
        }
    }

    @ProtectedForRole(UserRole.ADMIN)
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

    @ProtectedForRole(UserRole.ADMIN)
    @PostMapping("/")
    public Set<OutValidationAnswerDto> createPerson(@RequestBody InCreatePersonDto createPersonDto){
        Set<OutValidationAnswerDto> validationAnswerDtoSet = ValidationUtils.validateCreatePersonDto(createPersonDto);
        if(! validationAnswerDtoSet.isEmpty()){
            return validationAnswerDtoSet;
        }

        personRepository.save(createPersonDto.toEntity());
        return Collections.emptySet();
    }

    @ProtectedForRole(UserRole.USER)
    @PutMapping("/self")
    public Set<OutValidationAnswerDto> editSelf(@RequestBody InEditSelfDto editSelfDto){
        Set<OutValidationAnswerDto> validation = ValidationUtils.validateEditSelfDto(editSelfDto);
        if(! validation.isEmpty()){
            return validation;
        }

        return Collections.emptySet();
    }
}
