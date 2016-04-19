package com.tipico.social.marketing.gateway;

/**
 * Created by cmicallef on 19/04/2016.
 */
public interface TwitterService {

    /**
     * Tweets given message.
     *
     * @param message Message to be tweeted.
     */
    void tweetMessage(String message);
}
