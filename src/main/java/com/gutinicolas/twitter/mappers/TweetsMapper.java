package com.gutinicolas.twitter.mappers;

import com.gutinicolas.twitter.model.Tweet;
import com.gutinicolas.twitter.model.entities.TweetEntity;
import org.springframework.stereotype.Component;

@Component
public class TweetsMapper {

    public TweetEntity tweetToEntity(Tweet tweet) {
        TweetEntity entity = new TweetEntity();

        entity.setId(tweet.getId());
        entity.setContent(tweet.getContent());
        entity.setDislikes(tweet.getDislikes());
        entity.setLikes(tweet.getLikes());
        entity.setLocation(tweet.getLocation());
        entity.setOwnerUsername(tweet.getOwnerUsername());
        entity.setTweetedDate(tweet.getTweetedDate());

        return entity;
    }
}
