import {Component, OnInit} from 'angular2/core';
import {Campaign} from './campaign';
import {CampaignDetailComponent} from './campaign-detail.component';
import {CampaignsService} from './campaigns.service';

@Component({
    selector: 'my-campaigns',
    template: `
        <div id="list">
            <h2>Campaign List</h2>
            <ul class="items">
              <li *ngFor="#campaign of campaigns; #i = index"
                [class.selected]="campaign === selectedCampaign"
                (click)="onSelect(campaign)">
                <span class="badge">{{i + 1}}</span>
                <span class="text">{{campaign.eventName}}
                    <span class="delete-button" (click)="deleteCampaign(campaign)">
                        <img id="trash" src="app/trashbin.png" alt="Delete Campaign">
                    </span>
                </span>
              </li>
            </ul>
        </div>
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

    deleteCampaign(campaign: Campaign) {
        this._campaignsService.deleteCampaign(campaign.id).then(
            setTimeout("",1000); //so that getCampaigns will not return deleted campaign
            this.campaigns = undefined;
            this.getCampaigns();
        );
    }
}

