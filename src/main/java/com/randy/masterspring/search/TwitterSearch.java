package com.randy.masterspring.search;

import com.randy.masterspring.search.model.LightTweet;

import java.util.List;

public interface TwitterSearch {
    List<LightTweet> search(String searchType, List<String> keywords);
}
