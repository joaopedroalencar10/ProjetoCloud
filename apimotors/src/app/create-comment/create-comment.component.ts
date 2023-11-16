import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { CommentService } from '../services/comment.service';
import { CommentModel } from '../model/comment.model';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-create-comment',
  templateUrl: './create-comment.component.html',
  styleUrls: ['./create-comment.component.css'],
  providers: []

})
export class CreateCommentComponent {
  comentario = new FormControl('', [Validators.required]);
  @Output() newCommentEvent = new EventEmitter();
  @Input() idPost:any = '';

  constructor(private commentService: CommentService, private snackBar: MatSnackBar) {

  }

  public criarNovoComentario() {
    if (this.comentario.hasError("required")) {
      return;
    }

    let comment: CommentModel = {
      texto: this.comentario.value as string,
      data: new Date()
    };

    this.commentService.createComment(this.idPost, comment).subscribe(response => {
      this.snackBar.open("Comentário criado com sucesso", "Ok");
      this.newCommentEvent.emit();
    });
  }

}
