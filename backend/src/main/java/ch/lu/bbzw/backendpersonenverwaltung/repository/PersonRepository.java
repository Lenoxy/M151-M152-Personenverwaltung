package ch.lu.bbzw.backendpersonenverwaltung.repository;

import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonEntity, Long>{
    PersonEntity findById(String id);

    List<PersonEntity> findByFirstnameIgnoreCase(String firstname);

    List<PersonEntity> findByLastnameIgnoreCase(String lastname);

    List<PersonEntity> findByEmailIgnoreCase(String email);


}
