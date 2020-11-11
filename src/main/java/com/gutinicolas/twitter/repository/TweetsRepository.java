package com.gutinicolas.twitter.repository;

import com.gutinicolas.twitter.model.entities.TweetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TweetsRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public TweetEntity saveTweet(TweetEntity tweet) {
        mongoTemplate.save(tweet);
        return tweet;
    }

    public Optional<TweetEntity> find(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, TweetEntity.class));
    }

    public TweetEntity updateTweet(TweetEntity tweet) {
        return mongoTemplate.save(tweet);
    }

    public List<TweetEntity> findByUserId(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("ownerUsername").is(userId));
        return mongoTemplate.find(query, TweetEntity.class);
    }
}
