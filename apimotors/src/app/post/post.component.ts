import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import { PostService } from '../services/post.service';
import { Post } from '../model/post.model';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent  implements OnInit{
  posts: Post[]= [];

  constructor(private postService: PostService){

  }


  ngOnInit(): void {
    this.postService.getPosts().subscribe(response => {
      this.posts = response;
    })
  }

 /* ngBeforeViewInit(): void {

  }

  ngAfterViewInit(): void {

  }

  ngChanges(): void {

  }

  ngOnDestroy(): void {

  }
*/
}


