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

    List<PersonEntity> findAll();

    String removeById(String id);
}
