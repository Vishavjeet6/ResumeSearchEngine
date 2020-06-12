import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private _http : HttpClient) { }

  public addFilesFromRemote(formData: FormData):Observable<any>{
    return this._http.post<any>("http://localhost:8080/api/pdf/upload",formData);
  } 

  public getFilesFromRemote(id: number): Observable<any>{
    return this._http.get(`http://localhost:8080/api/getfile/${id}`);
  }
}
