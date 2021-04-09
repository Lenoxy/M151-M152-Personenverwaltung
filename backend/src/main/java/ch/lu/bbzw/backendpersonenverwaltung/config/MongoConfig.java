package ch.lu.bbzw.backendpersonenverwaltung.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig{
    //@Value("${s3.accessKey}")
    //private String uri = "mongodb://pers-db/person";

    //@Value("${s3.accessKey}")
    //private String host = "mongoserver";

    //@Value("${s3.accessKey}")
    //private int port = 27017;


    public @Bean MongoClient mongoClient() {
        return MongoClients.create("mongodb://pers-db:27017");
    }


}
