package com.gutinicolas.twitter.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Value("${mongo.password}")
    public String mongoPassword;

    @Bean
    public MongoClient mongo() {
        //ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/test");
        // MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
               // .applyConnectionString(connectionString)
               // .build();

        // MongoClients.create(mongoClientSettings)
        return MongoClients.create("mongodb+srv://writer:"+mongoPassword+"@cluster0.jz4uu.mongodb.net/twitter?retryWrites=true&w=majority");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "twitter");
    }
}
