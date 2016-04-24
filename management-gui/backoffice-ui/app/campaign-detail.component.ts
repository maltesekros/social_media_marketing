import {Component, Input} from 'angular2/core';
import {Campaign} from './campaign';

@Component({
    selector: 'campaign-detail',
    template: `
        <div *ngIf="campaign">
          <h2>{{campaign.eventName}} details:</h2>
          <div><label>id: </label>{{campaign.id}}</div>
          <div>
            <label>name: </label>
            <input [(ngModel)]="campaign.eventName" placeholder="name"/>
          </div>
        </div>
    `
})

export class CampaignDetailComponent {
    @Input()
    campaign: Campaign;
}