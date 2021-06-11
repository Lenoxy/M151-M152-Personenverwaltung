package ch.lu.bbzw.backendpersonenverwaltung.service;

import ch.lu.bbzw.backendpersonenverwaltung.dto.httpException.NotAuthorizedException;
import ch.lu.bbzw.backendpersonenverwaltung.dto.httpException.NotFoundException;
import ch.lu.bbzw.backendpersonenverwaltung.dto.out.OutLoginResponseDto;
import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;
import ch.lu.bbzw.backendpersonenverwaltung.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService{

    private final PersonRepository personRepository;

    public PasswordEncoder encoder = new Pbkdf2PasswordEncoder("terces");

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
        PersonEntity personEntity = personRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(NotAuthorizedException::new);

        if(encoder.matches(password, personEntity.getPassword())){
            return personEntity;
        }else{
            throw new NotAuthorizedException();
        }

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

        personEntity.setPassword(encoder.encode(password));
        return personRepository.save(personEntity);
    }

    public void changePassword(String username, String oldPasswordTypedByUser, String newPassword){
        PersonEntity personEntity = personRepository.findByUsernameIgnoreCase(username).orElseThrow(NotFoundException::new);
        if(!encoder.matches(oldPasswordTypedByUser, personEntity.getPassword())){
            throw new NotAuthorizedException();
        }

        personEntity.setPassword(encoder.encode(newPassword));
        personRepository.save(personEntity);
    }

}
