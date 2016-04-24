import {Component, OnInit} from 'angular2/core';
import {Campaign} from './campaign';
import {CampaignDetailComponent} from './campaign-detail.component';
import {CampaignsService} from './campaigns.service';

@Component({
    selector: 'my-campaigns',
    template: `
        <h2>Campaign List</h2>
        <ul class="items">
          <li *ngFor="#campaign of campaigns"
            [class.selected]="campaign === selectedCampaign"
            (click)="onSelect(campaign)">
            <span class="badge">{{campaign.id}}</span><span class="text">{{campaign.eventName}}</span>
          </li>
        </ul>
        <campaign-detail [campaign]="selectedCampaign"></campaign-detail>
    `,
    directives: [CampaignDetailComponent]
})
export class CampaignsComponent implements OnInit {
    campaigns: Campaign[];
    selectedCampaign: Campaign;

    constructor(private _campaignsService: CampaignsService) { }

    getCampaigns() {
        this._campaignsService.getCampaigns().then(campaigns => this.campaigns = Array.from(campaigns));
    }

    ngOnInit() {
        this.getCampaigns();
    }

    onSelect(campaign: Campaign) { this.selectedCampaign = campaign; }
}

