import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/service/file.service';
import { Search } from 'src/app/model/search';
import { ElasticService } from 'src/app/service/elastic.service';
import { Result } from 'src/app/model/result';

@Component({
  selector: 'app-adminhome',
  templateUrl: './adminhome.component.html',
  styleUrls: ['./adminhome.component.css']
})
export class AdminhomeComponent implements OnInit {
  search = new Search();
  results: Array<Result> = [];

  constructor(private _elasticService: ElasticService,
    private _fileService: FileService) { }

  ngOnInit(): void {
  }

  clean(obj) {
    for (var propName in obj) { 
      if (obj[propName] === null || obj[propName] === undefined || obj[propName] === "") {
        delete obj[propName];
      }
    }
  }
  

  doSearch(){
    console.log("yes")
    this.clean(this.search);
    console.log(this.search);
    this._elasticService.searchDescriptionFromRemote(this.search).subscribe(
      data =>{
        console.log(data);
        this.results = data;
      },
      error => {
        console.log(error);
      }
    )
  }

  openPdf(id: number){
    this._fileService.getFilesFromRemote(id).subscribe(
      data =>{
        console.log("File Fetched Successfully");
        const byteString = atob(data.fileByte);
        const arrayBuffer = new ArrayBuffer(byteString.length);
        const int8Array = new Uint8Array(arrayBuffer);
        for (let i = 0; i < byteString.length; i++) {
        int8Array[i] = byteString.charCodeAt(i);
      }
        const file = new Blob([int8Array], {type: data.type});
        const fileURL = URL.createObjectURL(file);
        window.open(fileURL, '_blank');
      },
      error => {
        console.log("File Cannot be Fetched");
        console.log(error);
      });
  }
}
