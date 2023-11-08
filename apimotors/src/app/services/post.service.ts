import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../model/post.model';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private httpClient: HttpClient) { }

public getPosts() : Observable<Post[]> {
  return this.httpClient.get<Post[]>("https://apiimotors.azurewebsites.net/swagger-ui/index.html#/")
}


public getPostsbyId(id : number) : Observable<Post> {
  return this.httpClient.get<Post>("https://apiimotors.azurewebsites.net/swagger-ui/index.html#/" + id)
}
}
