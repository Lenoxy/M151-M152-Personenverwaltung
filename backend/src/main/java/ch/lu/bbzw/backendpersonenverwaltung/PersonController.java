package ch.lu.bbzw.backendpersonenverwaltung;

import ch.lu.bbzw.backendpersonenverwaltung.config.MongoConfig;
import ch.lu.bbzw.backendpersonenverwaltung.dto.SinglePersonDto;
import ch.lu.bbzw.backendpersonenverwaltung.dto.QueryPersonDto;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/person")
public class PersonController{
    private final MongoConfig mongoConfig;

    @Autowired
    public PersonController(final MongoConfig mongoConfig){
        this.mongoConfig = mongoConfig;
    }


    @GetMapping("/{id}")
    public SinglePersonDto getPersonById(@PathVariable int id){
        return null;
    }

    @GetMapping("query/{property}/{value}")
    public List<QueryPersonDto> query(@PathVariable String property, @PathVariable String value){
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean removePerson(@PathVariable int id){
        return false;
    }

    @PutMapping("/{id}")
    public boolean editPerson(@PathVariable int id, @RequestBody SinglePersonDto singlePersonDto){
        return false;
    }

    @PutMapping("/")
    public boolean createPerson(@RequestBody SinglePersonDto singlePersonDto){
        return false;
    }

    @PostMapping("/self")
    public boolean editSelf(@RequestBody SinglePersonDto singlePersonDto){
        return false;
    }


    @GetMapping("/")
    public String example(){
        Document document = new Document("type", "exam");
        document.append("score", 300);
        mongoConfig.mongoClient().getDatabase("person").getCollection("person").insertOne(document);
        return "Greetings from Spring boot";
    }
}
