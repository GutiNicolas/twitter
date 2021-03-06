package com.gutinicolas.twitter.controller;

import com.gutinicolas.twitter.exceptions.TwitterException;
import com.gutinicolas.twitter.model.api.TweetRequest;
import com.gutinicolas.twitter.model.entities.TweetEntity;
import com.gutinicolas.twitter.service.TweetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tweets")
public class TweetsController {

    @Autowired
    TweetsService tweetsService;

    @PostMapping("/intent")
    public Map<String, Object> intentTweet(@Valid @RequestBody TweetRequest tweetRequest, HttpServletRequest request) throws TwitterException {
        return tweetsService.intentTweet(tweetRequest);
    }

    @PostMapping("/emotions/add")
    public Map<String, Object> addEmotion(@Valid @RequestBody Map<String, Object> emotionsRequest, HttpServletRequest request) throws TwitterException {
        return tweetsService.addEmotion(emotionsRequest);
    }

    @GetMapping("/{userId}")
    public List<TweetEntity> tweetsByUser(@PathVariable String userId) throws TwitterException {
        List<TweetEntity> found = tweetsService.findByUserId(userId);

        if (found == null || found.size() == 0) {
            throw new TwitterException(HttpStatus.BAD_REQUEST.value(), "Tweets not found for user "+userId);
        }
        return found;
    }

    @GetMapping("/tweet/{tweetId}")
    public TweetEntity findTweet(@PathVariable String tweetId) throws TwitterException {
        return tweetsService.findTweet(tweetId);
    }
}
