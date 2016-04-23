import {Component} from 'angular2/core';

export class Campaign {
    id: number;
    name: string;
}

@Component({
    selector: 'my-app',
    template: `
    <h1>{{title}}</h1>


    <h2>Campaign List</h2>
    <ul class="campaigns">
      <li *ngFor="#campaign of campaigns"
        [class.selected]="campaign === selectedCampaign"
        (click)="onSelect(campaign)">
        <span class="badge"></span> {{campaign.name}}
      </li>
    </ul>
    <div *ngIf="selectedCampaign">
      <h2>{{selectedCampaign.name}} details:</h2>
      <div><label>id: </label>{{selectedCampaign.id}}</div>
      <div>
        <label>name: </label>
        <input [(ngModel)]="selectedCampaign.name" placeholder="name"/>
      </div>
    </div>
    `
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
