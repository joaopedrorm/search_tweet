import { Component } from '@angular/core';
import {NgForm} from '@angular/forms';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
@Injectable()
export class AppComponent {

  constructor(private http: HttpClient) { }

  title = 'search-twitter-front';

  result: any;

  onSubmit(form: NgForm) {
    console.log('Your form data : ', form.value);
    console.log('Your form data : ', form.value.tag);
    console.log('Your form data : ', form.value.limit);

    this.searchTweetsByTag(form.value.tag, form.value.limit)
      .subscribe( response => {
        this.result = response;
        console.log('Result: ', this.result);
      }) ;

    
  }

  public searchTweetsByTag(tag: string, limit: string) {
    let urlSearchByTag: string = 'http://localhost:8080/twitter/search/tag/'+ tag.replace('#','%23') +'?limit='+ limit;
    console.log(urlSearchByTag);
    return this.http.get<any>(urlSearchByTag);
  }

  items = [
    '#apifirst',
    '#devops',
    '#cloudfirst',
    '#microservices',
    '#apigateway',
    '#oauth',
    '#swagger',
    '#raml',
    '#openapis'
  ]

  
}


