import {Injectable} from 'angular2/core';
import {Http, Response, Headers, RequestOptions} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {Campaign} from './campaign.js';

@Injectable()
export class CampaignsService {

    constructor (private http: Http) {}

    private _getPagedCampaignsUrl = 'http://localhost:8082/getPagedCampaigns?pageNumber=0&perPage=50';
    private _deleteCampaignUrl = 'http://localhost:8082/deleteCampaign?id=';
    private _saveCampaignUrl = 'http://localhost:8082/saveCampaign?campaign=';

    getCampaigns(): Promise<Campaign[]> {

        return this.http.get(this._getPagedCampaignsUrl)
            .map(this.extractData)
            .toPromise()
            .catch(this.handleError);
    }

    deleteCampaign(id: String): Promise {
     return this.http.get(this._deleteCampaignUrl + id)
     .toPromise()
     .catch(this.handleError);
     }

    saveCampaign(campaign: Campaign): Promise {
        var str = this.getSaveString(campaign);
        return this.http.get(str)
            .toPromise()
            .catch(this.handleError);
    }

    getSaveString(campaign: Campaign): String{
        return this._saveCampaignUrl + encodeURIComponent('{\"eventName\":\"' + campaign.eventName + '\",\"created\":\"' +
                '2016-04-28T06:20:00.000+02:00' + '\",\"lastUpdated\":\"' + '2016-04-28T06:20:00.000+02:00' +
                '\",\"startDate\":\"' + campaign.startDate + ':00.000+02:00\",\"endDate\":\"' + campaign.endDate +
                ':00.000+02:00\",\"delayBetweenPosts\":' + campaign.delayBetweenPosts + ',\"message\":\"' + campaign.message +
                '\"}');
    }

    private extractData(res: Response) {
        if (res.status < 200 || res.status >= 300) {
            throw new Error('Bad response status: ' + res.status);
        }
        return res.json() || { };
    }
    private handleError (error: any) {
        // In a real world app, we might send the error to remote logging infrastructure
        let errMsg = error.message || 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }
}