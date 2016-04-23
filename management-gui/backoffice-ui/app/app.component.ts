import {Component} from 'angular2/core';
import {CampaignDetailComponent} from './campaign-detail.component';

@Component({
    selector: 'my-app',
    template: `
    <h1>{{title}}</h1>


    <h2>Campaign List</h2>
    <ul class="items">
      <li *ngFor="#campaign of campaigns"
        [class.selected]="campaign === selectedCampaign"
        (click)="onSelect(campaign)">
        <span class="badge"></span><span class="text">{{campaign.name}}</span>
      </li>
    </ul>
    <campaign-detail [campaign]="selectedCampaign"></campaign-detail>
    `,
    directives: [CampaignDetailComponent]
})
export class AppComponent {
    title = 'Tipico Social Media Campaigns';
    campaigns = CAMPAIGNS;
    selectedCampaign: Campaign;
    onSelect(campaign: Campaign) { this.selectedCampaign = campaign; }
}

var CAMPAIGNS: Campaign[] = [
    { "id": 11, "name": "Mr. Nice" },
    { "id": 12, "name": "Narco" },
    { "id": 13, "name": "Bombasto" },
    { "id": 14, "name": "Celeritas" },
    { "id": 15, "name": "Magneta" },
    { "id": 16, "name": "RubberMan" },
    { "id": 17, "name": "Dynama" },
    { "id": 18, "name": "Dr IQ" },
    { "id": 19, "name": "Magma" },
    { "id": 20, "name": "Tornado" }
];
