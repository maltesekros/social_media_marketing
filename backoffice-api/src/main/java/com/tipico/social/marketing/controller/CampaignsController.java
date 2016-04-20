package com.tipico.social.marketing.controller;

import com.tipico.social.marketing.entity.Campaign;
import com.tipico.social.marketing.repository.CampaignsRepository;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by ncamilleri on 16/04/16.
 */

@RestController
@SpringBootApplication
@ComponentScan(basePackages = {"com.tipico.social.marketing"})
@EnableMongoRepositories(basePackageClasses=CampaignsRepository.class)
@RequestMapping("/")
public class CampaignsController {

    @Autowired
    private CampaignsRepository campaignsRepository;

    @RequestMapping("/")
    public String root() {
        return "Hello, Campaigns REST API is working!";
    }

    @RequestMapping("/getCampaign")
    @ResponseBody
    public Campaign getCampaign(@RequestParam(value="id", required=true) String id) {
        try {
            return campaignsRepository.findOne(Integer.parseInt(id));
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/saveCampaign")
    @ResponseBody
    public Campaign saveCampaign(@RequestParam(value="campaign", required=true) String campaignJson) {
        try {
            Campaign campaign = new ObjectMapper().readValue(campaignJson, Campaign.class);
            return campaignsRepository.insert(campaign);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/deleteCampaign")
    @ResponseBody
    public void deleteCampaign(@RequestParam(value="id", required=true) String id) {
        try {
            Campaign campaign = campaignsRepository.findOne(Integer.parseInt(id));
            campaignsRepository.delete(campaign);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @RequestMapping("/updateCampaign")
    @ResponseBody
    public Campaign updateCampaign(@RequestParam(value="campaign", required=true) String campaignJson) {
        try {
            Campaign campaign = new ObjectMapper().readValue(campaignJson, Campaign.class);
            return campaignsRepository.save(campaign);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/countCampaign")
    @ResponseBody
    public long countCampaign() {
        try {
            return campaignsRepository.count();
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
            campaignList = campaignsRepository.findAll(new PageRequest(pageNumber, perPage)).getContent();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return campaignList;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CampaignsController.class, args);
    }
}
