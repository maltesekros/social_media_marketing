package com.tipico.social.marketing.gateway;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cmicallef on 19/04/2016.
 */
@Controller
@RequestMapping("/social/twitter")
public class TwitterController {

    @RequestMapping(value = "{message}", method = RequestMethod.GET, headers = {"Content-type=application/json"})
    public
    @ResponseBody TwitterResponse sendTweetMessage(@PathVariable String message) {
        return new TwitterResponse(0, "OK");
    }
}
