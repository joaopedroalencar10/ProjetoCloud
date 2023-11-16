import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Post } from 'src/app/model/post.model';
import { CommentService } from 'src/app/services/comment.service';
import { PostService } from 'src/app/services/post.service';
import { CommentModel } from 'src/app/model/comment.model';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})

export class DetailComponent implements OnInit {

  post?: Post;
  comment?: CommentModel[];
  showCriarComentario = false;

  constructor(private postService: PostService,
    private commentService: CommentService,
    private route: ActivatedRoute) {

  }
  ngOnInit(): void {

    let idPost = this.route.snapshot.params["idPost"];
    this.postService.getPostsbyId(idPost).subscribe(response => {
      this.post = response;
      console.log(response);
    });

    this.carregaComentario();
  }

  private carregaComentario() {
    let idPost = this.route.snapshot.params["idPost"];
    this.commentService.getComment(idPost).subscribe(response => {
      this.comment = response;
    });
  }

  public mostrarCriarComentario() {
    this.showCriarComentario = true;
  }

  public atualizarComentarios() {
    this.carregaComentario();
  }

}
