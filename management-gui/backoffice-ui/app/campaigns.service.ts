import {Injectable} from 'angular2/core';
import {Http, Response, Headers, RequestOptions} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {Campaign} from './campaign';
import {CAMPAIGNS} from './mock-campaigns';

@Injectable()
export class CampaignsService {

    constructor (private http: Http) {}

    private _campaignsUrl = 'http://localhost:8082/getPagedCampaigns?pageNumber=0&perPage=50';  // URL to web api

    getCampaigns(): Promise<Campaign[]> {

        return this.http.get(this._campaignsUrl)
            .map(this.extractData)
            .toPromise();
            //.catch(this.handleError);
    }

    getCampaignsMocked() {
        return Promise.resolve(CAMPAIGNS);
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