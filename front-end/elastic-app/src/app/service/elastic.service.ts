import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Search } from '../model/search';

@Injectable({
  providedIn: 'root'
})
export class ElasticService {

  constructor(private _http : HttpClient) { }

  public searchDescriptionFromRemote(search: Search):Observable<any>{
    return this._http.post<any>("http://localhost:8080/api/pdf/description/find", search);
  } 
}
