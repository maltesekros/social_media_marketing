import {Injectable} from 'angular2/core';
import {Campaign} from './campaign';
import {CAMPAIGNS} from './mock-campaigns';

@Injectable()
export class CampaignService {
    getCampaigns() {
        return Promise.resolve(CAMPAIGNS);
    }
}