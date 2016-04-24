import {Component, Input} from 'angular2/core';
import {Campaign} from './campaign';

@Component({
    selector: 'campaign-detail',
    template: `
        <div *ngIf="campaign">
          <h2>{{campaign.eventName}} details:</h2>
          <div>
            <table id="campaignDetails">
                <tbody>
                    <tr><td><label>Campaign id: </label></td><td class="editable">{{campaign.id}}</td></tr>
                    <tr><td><label>Event name: </label></td><td class="editable"><input [(ngModel)]="campaign.eventName" placeholder="name"/></td></tr>
                    <tr><td><label>Start date: </label></td><td class="editable"><input [(ngModel)]="campaign.startDate" placeholder="start"/></td></tr>
                    <tr><td><label>End date: </label></td><td class="editable"><input [(ngModel)]="campaign.endDate" placeholder="end"/></td></tr>
                    <tr><td><label>Posting delay: </label></td><td class="editable"><input [(ngModel)]="campaign.delayBetweenPosts" placeholder="delay"/></td></tr>
                    <tr><td><label>Message: </label></td><td class="editable"><input id="message" [(ngModel)]="campaign.message" placeholder="message"/></td></tr>
                </tbody>
            </table>
          </div>
        </div>
    `
})

export class CampaignDetailComponent {
    @Input()
    campaign: Campaign;
}