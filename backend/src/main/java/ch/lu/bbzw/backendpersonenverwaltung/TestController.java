package ch.lu.bbzw.backendpersonenverwaltung;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{

    private final MongoClient mongoClient;


    @Autowired
    public TestController(final MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }


    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
