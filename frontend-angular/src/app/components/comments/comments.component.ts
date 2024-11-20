import { Component, OnInit } from '@angular/core';
import { CommentService } from '../../services/comment.service';
import { Comment } from '../../models/comment';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  comments: Comment[] = [];
  newComment: Comment = { id: 0, text: '', userId: 0, contentId: 0 };
  selectedComment: Comment | null = null;
  contentId: number = 1; // Exemple : ID de contenu fixe pour la démonstration

  constructor(private commentService: CommentService) { }

  ngOnInit(): void {
    this.loadComments();
  }

  // Charger les commentaires par contentId
  loadComments(): void {
    this.commentService.getCommentsByContentId(this.contentId).subscribe((data: Comment[]) => {
      this.comments = data;
    });
  }

  // Ajouter un nouveau commentaire
  addComment(): void {
    if (this.newComment.text.trim()) {
      this.newComment.contentId = this.contentId; // Associe le commentaire au contenu
      this.commentService.createComment(this.newComment).subscribe((createdComment: Comment) => {
        this.comments.push(createdComment); // Ajoute le commentaire à la liste
        this.newComment = { id: 0, text: '', userId: 0, contentId: 0 }; // Réinitialise le formulaire
      });
    }
  }

  // Sélectionner un commentaire pour modification
  selectCommentForEdit(comment: Comment): void {
    this.selectedComment = { ...comment }; // Clone l'objet pour éviter les modifications directes
  }

  // Mettre à jour le commentaire sélectionné
  updateComment(): void {
    if (this.selectedComment && this.selectedComment.id !== undefined) {
      this.commentService.updateComment(this.selectedComment.id, this.selectedComment).subscribe((updatedComment: Comment) => {
        const index = this.comments.findIndex(c => c.id === updatedComment.id);
        if (index !== -1) {
          this.comments[index] = updatedComment;
        }
        this.selectedComment = null; // Désélectionne le commentaire
      });
    }
  }
  

  // Supprimer un commentaire
  deleteComment(commentId: number | undefined): void {
    if (commentId !== undefined) {
      this.commentService.deleteComment(commentId).subscribe(() => {
        this.comments = this.comments.filter(c => c.id !== commentId);
      });
    }
  }
  

  // Annuler la sélection d'un commentaire pour modification
  cancelEdit(): void {
    this.selectedComment = null;
  }
}
