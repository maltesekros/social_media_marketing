import {Component, OnInit} from 'angular2/core';
import {Campaign} from './campaign.js';
import {CampaignDetailComponent} from './campaign-detail.component.js';
import {CampaignsService} from './campaigns.service.js';

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
                        <img id="trash" src="trashbin.png" alt="Delete Campaign">
                    </span>
                </span>
              </li>
              <button class="btn" id="addCampaignBtn" (click)="addCampaign()">Add Campaign</button>
            </ul>
        </div>
        <campaign-detail [campaign]="selectedCampaign"></campaign-detail>
        <table id="saveTable">
            <tbody>
                <tr><td></td><td><button class="btn" id="saveCampaignBtn" (click)="saveCampaign(selectedCampaign)">Save Campaign</button></td></tr>
            </tbody>
        </table>
    `,
    directives: [CampaignDetailComponent]
})
export class CampaignsComponent implements OnInit {
    campaigns: Campaign[];
    selectedCampaign: Campaign = new Campaign();

    constructor(private _campaignsService: CampaignsService) { }

    getCampaigns() {
        this._campaignsService.getCampaigns().then(campaigns => this.campaigns = Array.from(campaigns));
    }

    ngOnInit() {
        this.getCampaigns();
    }

    onSelect(campaign: Campaign) { this.selectedCampaign = campaign; }

    deleteCampaign(campaign: Campaign) {
        this.onSelect(campaign);
        this._campaignsService.deleteCampaign(campaign.id);
        this.getCampaigns();
    }

    addCampaign() {
        this.selectedCampaign = new Campaign();
    }

    saveCampaign(campaign) {
        this.selectedCampaign = campaign;
        this.campaigns.push(this.selectedCampaign);
        this._campaignsService.saveCampaign(this.selectedCampaign);
    }
}

