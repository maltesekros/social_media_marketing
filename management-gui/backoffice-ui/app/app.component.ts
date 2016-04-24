import {Component} from '/angular2/core';
import {CampaignsService} from './campaigns.service';
import {CampaignsComponent} from './campaigns.component';

@Component({
    selector: 'my-app',
    template:  `
        <h1>{{title}}</h1>
        <my-campaigns></my-campaigns>
    `,
    directives: [CampaignsComponent],
    providers: [CampaignsService]
})

export class AppComponent {
    title = 'Tipico Social Media Campaigns';
}