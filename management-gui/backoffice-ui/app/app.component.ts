import {Component, OnInit} from 'angular2/core';
import {Campaign} from './campaign';
import {CampaignDetailComponent} from './campaign-detail.component';
import {CampaignService} from './campaign.service';

@Component({
    selector: 'my-app',
    template: `
    <h1>{{title}}</h1>

    <h2>Campaign List</h2>
    <ul class="items">
      <li *ngFor="#campaign of campaigns"
        [class.selected]="campaign === selectedCampaign"
        (click)="onSelect(campaign)">
        <span class="badge">{{campaign.id}}</span><span class="text">{{campaign.name}}</span>
      </li>
    </ul>
    <campaign-detail [campaign]="selectedCampaign"></campaign-detail>
    `,
    directives: [CampaignDetailComponent],
    providers: [CampaignService]
})
export class AppComponent implements OnInit {
    title = 'Tipico Social Media Campaigns';
    campaigns: Campaign[];
    selectedCampaign: Campaign;

    constructor(private _campaignService: CampaignService) { }

    getCampaigns() {
        this._campaignService.getCampaigns().then(campaigns => this.campaigns = campaigns);
    }

    ngOnInit() {
        this.getCampaigns();
    }

    onSelect(campaign: Campaign) { this.selectedCampaign = campaign; }
}

