package com.gutinicolas.twitter.repository;

import com.gutinicolas.twitter.model.entities.TweetEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TweetsRepository {

    public TweetEntity saveTweet(TweetEntity tweet) {
        return tweet;
    }

    public Optional<TweetEntity> find(String id) {
        return Optional.empty();
    }

    public TweetEntity updateTweet(TweetEntity tweet) {
        return tweet;
    }

    public List<TweetEntity> findByUserId(String userId) {
        return List.of();
    }
}
