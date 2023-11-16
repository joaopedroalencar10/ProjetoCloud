import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import { PostService } from '../services/post.service';
import { Post } from '../model/post.model';
import { Router } from '@angular/router';
@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent  implements OnInit{
  posts: Post[]= [];

  constructor(private postService: PostService, private router: Router) {

  }

  ngOnInit(): void {
    this.postService.getPosts().subscribe(response => {
      this.posts = response;
    });
  }

  redirectToDetail(id: any) {
      this.router.navigate(["detail", id]);
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


