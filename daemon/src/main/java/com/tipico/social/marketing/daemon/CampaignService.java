package com.tipico.social.marketing.daemon;

import com.tipico.social.marketing.contract.Campaign;

import java.util.List;

/**
 * Created by chrism on 28/04/2016.
 */
public interface CampaignService {

	List<Campaign> getAllCampaigns();
}
