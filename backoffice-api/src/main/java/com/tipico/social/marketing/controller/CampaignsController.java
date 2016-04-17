package com.tipico.social.marketing.controller;

import com.tipico.social.marketing.contract.Campaign;
import com.tipico.social.marketing.repository.CampaignsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ncamilleri on 16/04/16.
 */

@RestController
@SpringBootApplication
@ComponentScan(basePackages = {"com.tipico.social.marketing"})
public class CampaignsController {

    @Autowired
    private CampaignsProvider campaignsProvider;

    @RequestMapping("/")
    public String root() {
        return "Hello, Campaigns REST API is working!";
    }

    @RequestMapping("/getCampaignDetails")
    @ResponseBody
    public Campaign getCampaignDetails(@RequestParam(value="id", required=true) String id) {
        return campaignsProvider.findOne(id);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CampaignsController.class, args);
    }
}
