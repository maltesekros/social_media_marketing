package com.tipico.social.marketing.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tipico.social.marketing.contract.Campaign;
import com.tipico.social.marketing.repository.CampaignsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.TimeZone;

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
            if (campaign != null) campaignsRepository.delete(campaign);
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

    public List<Campaign> getPagedCampaigns(int pageNumber, int perPage) {
        List<Campaign> campaignList = null;
        try {
            campaignList = campaignsRepository.findAll(new PageRequest(pageNumber, perPage)).getContent();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return campaignList;
    }

    public String getMilliSecondsFromDateString(String dateTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date;
        try {
            date = sdf.parse("1970-01-01 " + dateTime);
            return "" + date.getTime();
        } catch (Throwable t){
            t.printStackTrace();
        }
        return "";
    }

    public String getDateStringFromMilliSeconds(String ms){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        //creating Date from millisecond
        Date thisDate = new Date(ms);
        return sdf.format(thisDate);
    }
}
