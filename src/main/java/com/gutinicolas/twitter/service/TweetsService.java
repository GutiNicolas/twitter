package com.gutinicolas.twitter.service;

import com.gutinicolas.twitter.exceptions.TwitterException;
import com.gutinicolas.twitter.mappers.TweetsMapper;
import com.gutinicolas.twitter.model.Tweet;
import com.gutinicolas.twitter.model.api.TweetRequest;
import com.gutinicolas.twitter.model.entities.TweetEntity;
import com.gutinicolas.twitter.repository.TweetsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class TweetsService {

    @Autowired
    UsersService usersService;

    @Autowired
    TweetsRepository tweetsRepository;

    @Autowired
    TweetsMapper tweetsMapper;

    public Map<String, Object> intentTweet(TweetRequest tweetRequest) throws TwitterException {
        if (!usersService.userExists(tweetRequest.getUsername())) {
            throw new TwitterException(HttpStatus.BAD_REQUEST.value(), "User not found on Twitter");
        }

        Tweet tweet = tweetRequest.getTweet();
        tweet.setId(UUID.randomUUID().toString());
        tweet.setOwnerUsername(tweetRequest.getUsername());
        tweet.setTweetedDate(LocalDateTime.now());
        TweetEntity entity = tweetsRepository.saveTweet(tweetsMapper.tweetToEntity(tweetRequest.getTweet()));
        return Map.of("ok", true, "message", "Tweet successfully sent", "tweetId", entity.getId());
    }

    public Map<String, Object> addEmotion(Map<String, Object> emotionRequest) throws TwitterException {
        String username = (String) emotionRequest.get("username");
        String tweetId = (String) emotionRequest.get("tweetId");
        Boolean like = (Boolean) emotionRequest.get("like");

        if (StringUtils.isBlank(username) || StringUtils.isBlank(tweetId)) {
            throw new TwitterException(HttpStatus.BAD_REQUEST.value(), "Username or TweetId cannot be empty.");
        }

        if (!usersService.userExists(username)) {
            throw new TwitterException(HttpStatus.BAD_REQUEST.value(), "User not found on Twitter");
        }

        Optional<TweetEntity> optionalTweetEntity = tweetsRepository.find(tweetId);

        if (optionalTweetEntity.isEmpty()) {
            throw new TwitterException(HttpStatus.BAD_REQUEST.value(), String.format("Tweet with id %s not found", tweetId));
        }

        String message;
        TweetEntity entity = optionalTweetEntity.get();
        if (Boolean.TRUE.equals(like)) {
            // Es like
            entity.setLikes(entity.getLikes()+1);
            message = "Tweet successfully like";
        } else {
            // Es dislike
            entity.setDislikes(entity.getDislikes()+1);
            message = "Tweet successfully dislike";
        }

        tweetsRepository.updateTweet(entity);
        return Map.of("ok", true, "message", message);
    }

    public List<TweetEntity> findByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new TwitterException(HttpStatus.BAD_REQUEST.value(), "Username cannot be empty.");
        }

        if (!usersService.userExists(userId)) {
            throw new TwitterException(HttpStatus.BAD_REQUEST.value(), "User not found on Twitter");
        }

        return tweetsRepository.findByUserId(userId);
    }

    public TweetEntity findTweet(String id) {
        return tweetsRepository.find(id).orElseThrow(() -> new TwitterException(HttpStatus.BAD_REQUEST.value(), "Tweet not found"));
    }
}
