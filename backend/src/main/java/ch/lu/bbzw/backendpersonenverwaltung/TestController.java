package ch.lu.bbzw.backendpersonenverwaltung;

import ch.lu.bbzw.backendpersonenverwaltung.config.MongoConfig;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController{

    private final MongoConfig mongoConfig;


    @Autowired
    public TestController(final MongoConfig mongoConfig) {
        this.mongoConfig = mongoConfig;
    }


    @RequestMapping("/")
    public String index() {
        Document document = new Document("type", "exam");
        document.append("score", 300);
        mongoConfig.mongoClient().getDatabase("person").getCollection("person").insertOne(document);
        return "Greetings from Spring Boot";
    }
}
