import {Component, Input} from 'angular2/core';
import {Campaign} from './campaign.js';

@Component({
    selector: 'campaign-detail',
    template: `
        <div class="detail" *ngIf="campaign">
          <h2>Campaign details:</h2>
          <div>
            <table id="campaignDetails">
                <tbody>
                    <!--<tr><td><label>Campaign id: </label></td><td>{{campaign.id}}</td></tr>-->
                    <tr><td><label>Event name: </label></td><td class="editable"><input [(ngModel)]="campaign.eventName" placeholder="name of campaign"/></td></tr>
                    <tr><td><label>Start date: </label></td><td class="editable"><input type="datetime-local" [(ngModel)]="campaign.startDate" placeholder="start date"/></td></tr>
                    <tr><td><label>End date: </label></td><td class="editable"><input type="datetime-local" [(ngModel)]="campaign.endDate" placeholder="end date"/></td></tr>
                    <tr><td><label>Posting delay: </label></td><td class="editable"><input [(ngModel)]="campaign.delayBetweenPosts" placeholder="delay in seconds"/></td></tr>
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