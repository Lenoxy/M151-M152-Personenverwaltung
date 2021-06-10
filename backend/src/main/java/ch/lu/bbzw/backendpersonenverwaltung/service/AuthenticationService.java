package ch.lu.bbzw.backendpersonenverwaltung.service;

import ch.lu.bbzw.backendpersonenverwaltung.dto.out.OutLoginResponseDto;
import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;
import ch.lu.bbzw.backendpersonenverwaltung.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService{

    private final PersonRepository personRepository;

    @Autowired
    public AuthenticationService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public OutLoginResponseDto checkRegistrationStatus(String username){
        Optional<PersonEntity> personEntityOptional = personRepository.findByUsernameIgnoreCase(username);
        if(! personEntityOptional.isPresent()){
            System.out.println("Could not find person by username: " + username);
            return OutLoginResponseDto.INVALID_USER;
        }
        PersonEntity personEntity = personEntityOptional.get();
        if(personEntity.getPassword() == null){
            return OutLoginResponseDto.NEEDS_PASSWORD;
        }
        return OutLoginResponseDto.HAS_PASSWORD;
    }

    public PersonEntity login(String username, String password){
        Optional<PersonEntity> personEntity = personRepository.findByUsernameIgnoreCaseAndPasswordIgnoreCase(username, password);
        return personEntity.orElse(null);
    }

    public PersonEntity initialLogin(String username, String password){
        Optional<PersonEntity> personEntityOptional = personRepository.findByUsernameIgnoreCase(username);
        if(! personEntityOptional.isPresent()){
            System.out.println("Could not find person by username: " + username);
            return null;
        }
        PersonEntity personEntity = personEntityOptional.get();

        if(personEntity.getPassword() != null){
            System.out.println("Person with username " + username + " has their password set. Use the login API for this use case.");
            return null;
        }

        personEntity.setPassword(password);
        return personRepository.save(personEntity);
    }

}
