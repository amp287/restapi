package com.amp.restapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;

@Configuration
@DependsOn("mongoTemplate")
public class Config {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void initIndexes() {
        mongoTemplate.indexOps(User.class)
            .ensureIndex(
                new Index().on("email", Sort.Direction.DESC).unique()
        );
    }
} 