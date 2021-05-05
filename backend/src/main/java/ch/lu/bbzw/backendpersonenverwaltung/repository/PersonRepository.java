package ch.lu.bbzw.backendpersonenverwaltung.repository;

import ch.lu.bbzw.backendpersonenverwaltung.config.MongoConfig;
import ch.lu.bbzw.backendpersonenverwaltung.dto.SinglePersonDto;
import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;
import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class PersonRepository{
    private final MongoCollection mongoCollection;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public PersonRepository(final MongoConfig mongoConfig){
        this.mongoCollection = mongoConfig.mongoClient()
                .getDatabase("person")
                .getCollection("person");
        this.mongoTemplate = mongoConfig.mongoTemplate();
    }

    public PersonEntity getById(String id){
        PersonEntity entity = mongoTemplate.findById(id, PersonEntity.class);

        return entity;

    }

    public void insert(PersonEntity personEntity){
        mongoTemplate.save(personEntity);
    }

}
