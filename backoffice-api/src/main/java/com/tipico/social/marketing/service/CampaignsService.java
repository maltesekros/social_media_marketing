package com.tipico.social.marketing.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tipico.social.marketing.contract.Campaign;
import com.tipico.social.marketing.repository.CampaignsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ncamilleri on 20/04/16.
 */
@Service
public class CampaignsService {
    @Autowired
    private MongoOperations mongo;

    @Autowired
    private CampaignsRepository campaignsRepository;

    public Campaign getCampaign(String id) {
        try {
            return campaignsRepository.findOne(id);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public Campaign saveCampaign(String campaignJson) {
        try {
            Campaign campaign = new ObjectMapper().readValue(campaignJson, Campaign.class);
            return campaignsRepository.insert(campaign);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public void deleteCampaign(String id) {
        try {
            Campaign campaign = campaignsRepository.findOne(id);
            campaignsRepository.delete(campaign);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public Campaign updateCampaign(String campaignJson) {
        try {
            Campaign campaign = new ObjectMapper().readValue(campaignJson, Campaign.class);
            return campaignsRepository.save(campaign);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public long countCampaigns() {
        try {
            return campaignsRepository.count();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return -1L;
    }

    public List<Campaign> requestPagedCampaigns(int pageNumber, int perPage) {
        List<Campaign> campaignList = null;
        try {
            campaignList = campaignsRepository.findAll(new PageRequest(pageNumber, perPage)).getContent();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return campaignList;
    }
}