package ch.lu.bbzw.backendpersonenverwaltung.repository;

import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, String>{

    Optional<PersonEntity> findById(String id);

    Optional<PersonEntity> findByUsernameIgnoreCase(String username);

    Optional<PersonEntity> findByUsernameIgnoreCaseAndPasswordIgnoreCase(String username, String password);

    List<PersonEntity> findByFirstnameIgnoreCase(String firstname);

    List<PersonEntity> findByLastnameIgnoreCase(String lastname);

    List<PersonEntity> findByEmailIgnoreCase(String email);

    String removeById(String id);
}
