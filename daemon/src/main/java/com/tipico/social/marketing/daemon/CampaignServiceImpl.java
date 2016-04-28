package com.tipico.social.marketing.daemon;

import com.tipico.social.marketing.contract.Campaign;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chrism on 28/04/2016.
 */
@Component
public class CampaignServiceImpl implements CampaignService {

	@Override
	public List<Campaign> getAllCampaigns() {
		// TODO - Get Campaigns from API
		Campaign testCampaign = new Campaign();
		testCampaign.setId("1234567");
		testCampaign.setCreated(new Date());
		testCampaign.setDelayBetweenPosts(600000);
		DateTime startDate = new DateTime();
		startDate = startDate.minusMinutes(1);
		testCampaign.setStartDate(startDate.toDate());
		DateTime endDate = startDate.plusMinutes(60);
		testCampaign.setEndDate(endDate.toDate());
		testCampaign.setEventName("UCL Athletico Madrid vs Bayern Munich");
		testCampaign.setMessage("Click here to bet on Bayern Munich turning it round next week! http://m.tipico.com/ng/35454?c=twiitter #UCL #AMFCB");

		List<Campaign> campaignsList = new ArrayList<>();
		campaignsList.add(testCampaign);

		return campaignsList;
	}
}
