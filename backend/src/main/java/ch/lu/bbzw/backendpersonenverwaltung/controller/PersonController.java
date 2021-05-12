package ch.lu.bbzw.backendpersonenverwaltung;

import ch.lu.bbzw.backendpersonenverwaltung.config.MongoConfig;
import ch.lu.bbzw.backendpersonenverwaltung.dto.QueryPersonDto;
import ch.lu.bbzw.backendpersonenverwaltung.dto.SinglePersonDto;
import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;
import ch.lu.bbzw.backendpersonenverwaltung.repository.PersonRepository;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/person")
public class PersonController{
    private final MongoCollection mongoCollection;
    private PersonRepository personRepository;

    @Autowired
    public PersonController(final MongoConfig mongoConfig, final PersonRepository personRepository){
        this.mongoCollection = mongoConfig.mongoClient()
                .getDatabase("person")
                .getCollection("person");

        this.personRepository = personRepository;
    }


    @GetMapping("/{id}")
    public PersonEntity getPersonById(@PathVariable String id){
        return personRepository.getById(id);
    }

    @GetMapping("query/{property}/{value}")
    public List<QueryPersonDto> query(@PathVariable String property, @PathVariable String value){
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean removePerson(@PathVariable String id){
        return false;
    }

    @PutMapping("/{id}")
    public boolean editPerson(@PathVariable String id, @RequestBody SinglePersonDto singlePersonDto){
        return false;
    }

    @PostMapping("/")
    public boolean createPerson(@RequestBody SinglePersonDto singlePersonDto){
        personRepository.insert(singlePersonDto.toEntity());
        return false;
    }

    @PutMapping("/self")
    public boolean editSelf(@RequestBody SinglePersonDto singlePersonDto){
        return false;
    }


//    @GetMapping("")
//    public String example(){
//        Document document = new Document("type", "exam");
//        document.append("score", 300);
//        mongoCollection.insertOne(document);
//        return "Greetings from Spring boot";
//    }
}
