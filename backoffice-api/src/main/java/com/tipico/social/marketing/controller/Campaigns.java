package com.tipico.social.marketing.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ncamilleri on 16/04/16.
 */

@RestController
@SpringBootApplication
public class Campaigns {

    @RequestMapping("/campaign")
    @ResponseBody
    public String getCampaign(@RequestParam(value="id", required=true) String id) {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Campaigns.class, args);
    }
}
