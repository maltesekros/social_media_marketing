package com.tipico.social.marketing.controller;

import com.tipico.social.marketing.contract.Campaign;
import com.tipico.social.marketing.repository.CampaignsProvider;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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

    @RequestMapping("/getCampaign")
    @ResponseBody
    public Campaign getCampaign(@RequestParam(value="id", required=true) String id) {
        return campaignsProvider.findOne(id);
    }

    @RequestMapping("/saveCampaign")
    @ResponseBody
    public Campaign saveCampaign(@RequestParam(value="campaign", required=true) String campaignJson) {
        try {
            Campaign campaign = new ObjectMapper().readValue(campaignJson, Campaign.class);
            return campaignsProvider.insert(campaign);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/deleteCampaign")
    @ResponseBody
    public void deleteCampaign(@RequestParam(value="id", required=true) String id) {
        try {
            Campaign campaign = campaignsProvider.findOne(id);
            campaignsProvider.delete(campaign);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @RequestMapping("/updateCampaign")
    @ResponseBody
    public Campaign updateCampaign(@RequestParam(value="campaign", required=true) String campaignJson) {
        try {
            Campaign campaign = new ObjectMapper().readValue(campaignJson, Campaign.class);
            return campaignsProvider.save(campaign);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/countCampaign")
    @ResponseBody
    public long countCampaign() {
        try {
            return campaignsProvider.count();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return -1L;
    }

    @RequestMapping("/requestPagedCampaigns")
    @ResponseBody
    public List<Campaign> requestPagedCampaigns(@RequestParam(value="pageNumber", required=true) int pageNumber,
                                                @RequestParam(value="campaignsPerPage", required=true) int perPage) {
        List<Campaign> campaignList = null;

        try {
            campaignList = campaignsProvider.findAll(new PageRequest(pageNumber, perPage)).getContent();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return campaignList;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CampaignsController.class, args);
    }
}
