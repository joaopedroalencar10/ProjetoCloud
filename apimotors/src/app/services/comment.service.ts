import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentModel } from '../model/comment.model';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  public getComment(idPost:any) : Observable<CommentModel[]> {
    return this.http.get<CommentModel[]>(`https://apiimotors.azurewebsites.net/comentario`);
  }

  public createComment(idPost: any, comment: CommentModel): Observable<CommentModel> {
    return this.http.post<CommentModel>(`https://apiimotors.azurewebsites.net/comentario/${idPost}`, comment);
  }
}

