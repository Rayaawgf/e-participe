import { Component, OnInit } from '@angular/core';
import { CommentService } from '../../services/comment.service';
import { Comment } from '../../models/comment'; // Utilisation du modèle Comment

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  comments: Comment[] = [];
  newComment: Comment = { text: '', userId: 1, contentId: 1 }; // IDs par défaut à modifier

  constructor(private commentService: CommentService) {}

  ngOnInit(): void {
    this.getAllComments();
  }

  // Récupérer tous les commentaires
  getAllComments(): void {
    this.commentService.getAllComments().subscribe((data) => {
      this.comments = data;
    });
  }

  // Ajouter un nouveau commentaire
  addComment(): void {
    this.commentService.createComment(this.newComment).subscribe((createdComment) => {
      this.comments.push(createdComment); // Ajouter le commentaire à la liste
      this.newComment = { text: '', userId: 1, contentId: 1 }; // Réinitialiser le formulaire
    });
  }

  // Supprimer un commentaire
  deleteComment(id: number): void {
    this.commentService.deleteComment(id).subscribe(() => {
      this.comments = this.comments.filter(comment => comment.id !== id); // Retirer le commentaire de la liste
    });
  }
}
