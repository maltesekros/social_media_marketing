package com.tipico.social.marketing.repository;

import com.tipico.social.marketing.contract.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * Created by ncamilleri on 16/04/16.
 */
@Component("campaignsRepository")
public interface CampaignsRepository extends MongoRepository<Campaign, String> {
}