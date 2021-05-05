package ch.lu.bbzw.backendpersonenverwaltung.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig{
    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create("mongodb://root:Person321@pers-db:27017");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "person");
    }
}
