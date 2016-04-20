package com.tipico.social.marketing.repository;

import com.tipico.social.marketing.contract.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * Created by ncamilleri on 16/04/16.
 */
@RepositoryDefinition(domainClass = Campaign.class, idClass = Integer.class)
public interface CampaignsRepository extends MongoRepository<Campaign, Integer> {}