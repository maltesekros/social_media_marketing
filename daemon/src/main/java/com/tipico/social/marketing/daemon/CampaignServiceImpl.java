package com.tipico.social.marketing.daemon;

import com.tipico.social.marketing.contract.Campaign;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by chrism on 28/04/2016.
 */
@Component
public class CampaignServiceImpl implements CampaignService {

	@Value("${backoffice-api}")
	private String backOfficeApiUrl;

	@Override
	public List<Campaign> getAllCampaigns() {
		List<Campaign> campaigns;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Campaign[]> response = restTemplate.getForEntity(backOfficeApiUrl + "/getPagedCampaigns?pageNumber=0&perPage=10", Campaign[].class);
		campaigns = new ArrayList<Campaign>(Arrays.asList(response.getBody()));
		return campaigns;
	}

	private List<Campaign> getDummyCampaigns() {
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
