package com.tipico.social.marketing.controller;

import com.tipico.social.marketing.contract.Campaign;
import com.tipico.social.marketing.repository.CampaignsRepository;
import com.tipico.social.marketing.service.CampaignsService;
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
    private CampaignsService campaignsService;

    @RequestMapping("/")
    public String root() {
        return "Hello, Campaigns REST API is working!";
    }

    @RequestMapping("/getCampaign")
    @ResponseBody
    public Campaign getCampaign(@RequestParam(value="id", required=true) String id) {
        return campaignsService.getCampaign(id);
    }

    @RequestMapping("/saveCampaign")
    @ResponseBody
    public Campaign saveCampaign(@RequestParam(value="campaign", required=true) String campaignJson) {
        return campaignsService.saveCampaign(campaignJson);
    }

    @RequestMapping("/deleteCampaign")
    @ResponseBody
    public void deleteCampaign(@RequestParam(value="id", required=true) String id) {
        campaignsService.deleteCampaign(id);
    }

    @RequestMapping("/updateCampaign")
    @ResponseBody
    public Campaign updateCampaign(@RequestParam(value="campaign", required=true) String campaignJson) {
        return campaignsService.updateCampaign(campaignJson);
    }

    @RequestMapping("/countCampaigns")
    @ResponseBody
    public long countCampaigns() {
        return campaignsService.countCampaigns();
    }

    @RequestMapping("/getPagedCampaigns")
    @ResponseBody
    public List<Campaign> getPagedCampaigns(@RequestParam(value="pageNumber", required=true) int pageNumber,
                                                @RequestParam(value="perPage", required=true) int perPage) {
        return campaignsService.getPagedCampaigns(pageNumber, perPage);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CampaignsController.class, args);
    }
}
