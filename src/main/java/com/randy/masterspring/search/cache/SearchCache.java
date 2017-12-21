package com.randy.masterspring.search.cache;

import com.randy.masterspring.search.SearchParamsBuilder;
import com.randy.masterspring.search.model.LightTweet;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.social.TwitterProperties;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.Cacheable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchCache {
    protected final org.apache.juli.logging.Log logger = LogFactory.getLog(getClass());
    private Twitter twitter;

    @Autowired
    public SearchCache(TwitterProperties twitterProperties){
        this.twitter = new TwitterTemplate(twitterProperties.getAppId(),twitterProperties.getAppSecret());
    }

    @Cacheable("searches")
    public List<LightTweet> fetch(String searchType, String keyword){
        logger.info("Cache miss for" + keyword);
        SearchParameters searchParameters = SearchParamsBuilder.createSearchParam(searchType,keyword);
        return twitter.searchOperations()
                .search(searchParameters)
                .getTweets().stream()
                .map(LightTweet::ofTweet)
                .collect(Collectors.toList());
    }

}
