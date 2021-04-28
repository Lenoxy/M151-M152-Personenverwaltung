package ch.lu.bbzw.backendpersonenverwaltung.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig{
    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create("mongodb://root:Person321@pers-db:27017");
    }
}
