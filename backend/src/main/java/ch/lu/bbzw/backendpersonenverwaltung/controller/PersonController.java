package ch.lu.bbzw.backendpersonenverwaltung.controller;

import ch.lu.bbzw.backendpersonenverwaltung.ValidationUtils;
import ch.lu.bbzw.backendpersonenverwaltung.dto.httpException.NotAuthorizedException;
import ch.lu.bbzw.backendpersonenverwaltung.dto.httpException.NotFoundException;
import ch.lu.bbzw.backendpersonenverwaltung.dto.in.InCreatePersonDto;
import ch.lu.bbzw.backendpersonenverwaltung.dto.in.InEditPersonDto;
import ch.lu.bbzw.backendpersonenverwaltung.dto.in.InEditSelfDto;
import ch.lu.bbzw.backendpersonenverwaltung.dto.in.InQueryPersonDto;
import ch.lu.bbzw.backendpersonenverwaltung.dto.out.OutValidationAnswerDto;
import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;
import ch.lu.bbzw.backendpersonenverwaltung.repository.PersonRepository;
import ch.lu.bbzw.backendpersonenverwaltung.service.JwtService;
import ch.lu.bbzw.backendpersonenverwaltung.security.ProtectedForRole;
import ch.lu.bbzw.backendpersonenverwaltung.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
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

        PersonEntity oldPersonEntity = personRepository.findById(id).orElseThrow(NotFoundException::new);

        PersonEntity newPersonEntity = editPersonDto.toEntity(oldPersonEntity);

        personRepository.save(newPersonEntity);

        return Collections.emptySet();
    }

    @ProtectedForRole(UserRole.USER)
    @PutMapping("/self")
    public Set<OutValidationAnswerDto> editSelf(
            @RequestBody InEditSelfDto editSelfDto,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwt
    ){
        String id = jwtService.getIdFromClaim(jwt);
        PersonEntity personEntity = personRepository.findById(id).orElseThrow(NotFoundException::new);

        Set<OutValidationAnswerDto> validation;

        if(personEntity.isAdmin()){
            validation = ValidationUtils.validateEditSelfDtoForAdmin(editSelfDto);
        }else{
            validation = ValidationUtils.validateEditSelfDtoForUser(editSelfDto);
        }
        if(! validation.isEmpty()){
            return validation;
        }

        if(personEntity.isAdmin()){
            personRepository.save(editSelfDto.toEntityWithAdminPermission(personEntity));
        }else{
            personRepository.save(editSelfDto.toEntityWithUserPermission(personEntity));
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
}
